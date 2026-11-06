package sisco.sc;

import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class HelloStructuredConcurrency {

	public static void main(String[] args) {
		
		Callable<String> task1 = () -> {
			System.out.println("task1 exec, by " + Thread.currentThread());
			Thread.sleep(1000);
			return "hello world";
		};
		Callable<Integer> task2 = () -> {
			System.out.println("task2 exec, by " + Thread.currentThread());
			Thread.sleep(3000);
			return 999;
		};

		try (var scope = new StructuredTaskScope<Object>()) {

			Subtask<String> subtask1 = scope.fork(task1);
			Subtask<Integer> subtask2 = scope.fork(task2);

			try {
				scope.join();
				System.out.println("join, by " + Thread.currentThread());
				System.out.println(subtask1.get() + " - " + subtask2.get());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
