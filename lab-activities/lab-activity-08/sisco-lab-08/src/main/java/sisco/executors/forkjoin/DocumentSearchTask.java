package sisco.executors.forkjoin;

import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask extends RecursiveTask<Long> {
    
	private final Document document;
    private final String searchedWord;
    private final WordCounter wc;
    
    public DocumentSearchTask(WordCounter wc, Document document, String searchedWord) {
        super();
        this.document = document;
        this.searchedWord = searchedWord;
        this.wc = wc;
    }
    
    @Override
    protected Long compute() {
        return wc.occurrencesCount(document, searchedWord);
    }
}

