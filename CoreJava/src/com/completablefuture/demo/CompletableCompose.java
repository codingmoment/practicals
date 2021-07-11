package com.completablefuture.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * This program demonstrates chaining of two CompletableFutures where you pass
 * the result of one CompletableFuture to the next CompletableFuture.
 * 
 * @author N.Wani
 */
public class CompletableCompose {
	public static void main(String[] args) {
		// Here we are spinning off a computation asynchronously.
		// The main thread (consumer) receives CompletableFuture.
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " - supplyAsync 1 started ...");
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " - supplyAsync 1 completed ...");
			} catch (InterruptedException e) {
			}
			return "Hello";
		}) // This is first CompletableFuture.

				// It GETS the result from previous CompletableFuture and RETURNS a new
				// CompletableFuture.
				.thenCompose(s -> CompletableFuture.supplyAsync(() -> {
					System.out.println(Thread.currentThread().getName() + " - supplyAsync 2 started ...");
					try {
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName() + " - supplyAsync 2 completed ...");
					} catch (InterruptedException e) {
					}
					return s + " World!";
				}));

		try {
			// The main thread blocks till all tasks are completed.
			String result = completableFuture.get();
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
