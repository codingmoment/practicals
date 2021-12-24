package com.completablefuture.demo;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This program demonstrates parallel execution of three CompletableFutures
 * where they execute independently and we process the returned values.
 * 
 * @author N.Wani
 *
 */
public class CompletableParallelJoin {
	public static void main(String[] args) {
		CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " - supplyAsync 1 started ...");
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " - supplyAsync 1 completed ...");
			} catch (InterruptedException e) {
			}
			return "Hello";
		});

		CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " - supplyAsync 2 started ...");
			try {
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + " - supplyAsync 2 completed ...");
			} catch (InterruptedException e) {
			}
			return "Beautiful";
		});

		CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " - supplyAsync 3 started ...");
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " - supplyAsync 3 completed ...");
			} catch (InterruptedException e) {
			}
			return "World!";
		});

		String combinedResult = Stream.of(completableFuture1, completableFuture2, completableFuture3)
				.map(CompletableFuture::join).collect(Collectors.joining(" "));
		System.out.println(combinedResult);
	}
}
