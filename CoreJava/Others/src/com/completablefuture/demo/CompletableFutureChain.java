package com.completablefuture.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * This class demonstrates usage of CompletableFuture to process a chain of
 * dependent tasks.
 * 
 * @author N.Wani
 *
 */
public class CompletableFutureChain {

	public static void main(String[] args) {
		// Here we are spinning off a computation asynchronously.
		// The main thread (consumer) receives CompletableFuture.
		CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " - supplyAsync started ...");
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " - supplyAsync completed ...");
			} catch (InterruptedException e) {
			}
			return "Hello World!";
		}) // This is task#1.
				// This is task#2, it GETS the result from task#1 and RETURNS new result.
				.thenApply(s -> {
					System.out.println(Thread.currentThread().getName() + " - thenApply started ...");
					try {
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName() + " - thenApply completed ...");
					} catch (InterruptedException e) {
					}
					return s.toUpperCase();
				})
				// This is task#3, it GETS the result from task#2 but DOES NOT RETURN any
				// result.
				.thenAccept(s -> {
					System.out.println(Thread.currentThread().getName() + " - thenAccept started ...");
					try {
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName() + " - thenAccept completed ...");
					} catch (InterruptedException e) {
					}
					System.out.println(s);
				})
				// This is task#4, it DOES NOT GET result from task#2 and DOES NOT RETURN any
				// result.
				.thenRun(() -> {
					System.out.println(Thread.currentThread().getName() + " - thenRun started ...");
					try {
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName() + " - thenRun completed ...");
					} catch (InterruptedException e) {
					}
				});

		try {
			// The main thread blocks till all tasks are completed.
			completableFuture.get();
			System.out.println("Completed...");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
