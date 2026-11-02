/*
 * Fork-Join example, adapted from
 * http://www.oracle.com/technetwork/articles/java/fork-join-422606.html
 * 
 */
package sisco.executors.forkjoin;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class WordCounterMain {    


    public static void main(String[] args) throws IOException {
    	
        WordCounter wordCounter = new WordCounter();
        Folder folder = Folder.fromDirectory(new File(args[0]));
        
        final int repeatCount = Integer.parseInt(args[2]);
        long counts;
        long startTime;
        long stopTime;
        
        long[] singleThreadTimes = new long[repeatCount];
        long[] forkedThreadTimes = new long[repeatCount];
        
        for (int i = 0; i < repeatCount; i++) {
            startTime = System.currentTimeMillis();
            counts = wordCounter.countOccurrencesOnSingleThread(folder, args[1]);
            stopTime = System.currentTimeMillis();
            singleThreadTimes[i] = (stopTime - startTime);
            System.out.println(counts + " , single thread search took " + singleThreadTimes[i] + "ms");
        }
        
        for (int i = 0; i < repeatCount; i++) {
            startTime = System.currentTimeMillis();
            counts = wordCounter.countOccurrencesInParallel(folder, args[1]);
            stopTime = System.currentTimeMillis();
            forkedThreadTimes[i] = (stopTime - startTime);
            System.out.println(counts + " , fork / join search took " + forkedThreadTimes[i] + "ms");
        }
        
        System.out.println("\nCSV Output:\n");
        System.out.println("Single thread,Fork/Join");        
        for (int i = 0; i < repeatCount; i++) {
            System.out.println(singleThreadTimes[i] + "," + forkedThreadTimes[i]);
        }
        System.out.println();
    }
}
