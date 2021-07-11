package com.completablefuture.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * This class demonstrates use of CompletableFuture as Future.
 * 
 * @author N.Wani
 */
public class CompletableAsFuture {

	public static void main(String[] args) {
		// Here we are spinning off a computation asynchronously.
		// The main thread (consumer) receives CompletableFuture.
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + " - Sleeping for 5 seconds ...");
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + " - Continuing again ...");
			} catch (InterruptedException e) {
			}
			return "Hello World";
		});

		try {
			System.out.println(Thread.currentThread().getName() + " - Getting the result ...");
			// The main thread (consumer) calls get() method on CompletableFeature.
			// The main thread is ready to block for the results.
			String result = completableFuture.get();
			System.out.println(Thread.currentThread().getName() + " - Result = " + result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
