package sisco.sc;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.StructuredTaskScope.*;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.function.*;
import java.util.stream.*;

public class TestStructuredConcurrency {
    
    class TooSlowException extends Exception {
        public TooSlowException(String s) {
            super(s);
        }
    }
    
    public Integer randomTask(int maxDuration, int threshold) throws InterruptedException, TooSlowException {
        int t = new Random().nextInt(maxDuration);
        System.out.println("Duration: " + t);
        if (t > threshold) {
            throw new TooSlowException("Duration " + t + " greater than threshold " + threshold);
        }
        Thread.sleep(t);
        return Integer.valueOf(t);        
    }        
    
    void handleShutdownOnFailure() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            // var t = new SCRandomTasks();
            var subtasks = IntStream.range(0, 5)
                                    .mapToObj(i -> scope.fork(() -> randomTask(1000, 850)))
                                    .toList();
            scope.join()
                 .throwIfFailed();
            var totalDuration = subtasks.stream()
                                        .map(t -> t.get())
                                        .reduce(0, Integer::sum);
            System.out.println("Total duration: " + totalDuration);
        }
    }
    
    void handleShutdownOnSuccess() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess()) {
            IntStream.range(0, 5)
                     .mapToObj(i -> scope.fork(() -> randomTask(1000, 850)))
                     .toList();
            scope.join();
            System.out.println("First task to finish: " + scope.result());
        }
    }    
    
    public static void main(String[] args) {
        var myApp = new TestStructuredConcurrency();
        try {
            System.out.println("Running handleShutdownOnFailure...");
            myApp.handleShutdownOnFailure();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Running handleShutdownOnSuccess...");
            myApp.handleShutdownOnSuccess();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }      
    }
}