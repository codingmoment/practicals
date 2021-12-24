package com.completablefuture.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * This program demonstrates chaining of two CompletableFutures where they
 * execute independently and later we process the results of them.
 * 
 * @author N.Wani
 *
 */
public class CompletableCombine {
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
			// This is second CompletableFuture.
			// We are combining the two CompletableFutures.
			// With passed Function, we are processing results of two CompletableFutures.
				.thenCombine(CompletableFuture.supplyAsync(() -> {
					System.out.println(Thread.currentThread().getName() + " - supplyAsync 2 started ...");
					try {
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + " - supplyAsync 2 completed ...");
					} catch (InterruptedException e) {
					}
					
					return " World!";
				}), (s1, s2) -> {
					System.out.println(Thread.currentThread().getName() + " - Function ...");
					return s1 + s2;
				});

		try {
			// The main thread blocks till all tasks are completed.
			String result = completableFuture.get();
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
