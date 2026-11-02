/*
 * Fork-Join example, adapted from
 * http://www.oracle.com/technetwork/articles/java/fork-join-422606.html
 * 
 */
package sisco.executors.forkjoin;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class WordCounter {    

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    
    public String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }
    
    public Long occurrencesCount(Document document, String searchedWord) {
        long count = 0;
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                if (searchedWord.equals(word)) {
                    count = count + 1;
                }
            }
        }
        return count;
    }
        
    public Long countOccurrencesOnSingleThread(Folder folder, String searchedWord) {
        long count = 0;
        for (Folder subFolder : folder.getSubFolders()) {
            count = count + countOccurrencesOnSingleThread(subFolder, searchedWord);
        }
        for (Document document : folder.getDocuments()) {
            count = count + occurrencesCount(document, searchedWord);
        }
        return count;
    }

    public Long countOccurrencesInParallel(Folder folder, String searchedWord) {
        return forkJoinPool.invoke(new FolderSearchTask(this, folder, searchedWord));
    }

}
