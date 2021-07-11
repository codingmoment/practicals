package com.completablefuture.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * This program demonstrates parallel execution of three CompletableFutures
 * where they execute independently.
 * 
 * @author N.Wani
 *
 */
public class CompletableParallel {
	public static void main(String[] args) {
		CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " - supplyAsync 1 started ...");
			try {
				Thread.sleep(10000);
				System.out.println(Thread.currentThread().getName() + " - supplyAsync 1 completed ...");
			} catch (InterruptedException e) {
			}
			return "Hello";
		});

		CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " - supplyAsync 2 started ...");
			try {
				Thread.sleep(5000);
				throw new RuntimeException();
				//System.out.println(Thread.currentThread().getName() + " - supplyAsync 2 completed ...");
			} catch (InterruptedException e) {
			}
			return " Beautiful";
		});

		CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " - supplyAsync 3 started ...");
			try {
				Thread.sleep(10000);
				System.out.println(Thread.currentThread().getName() + " - supplyAsync 3 completed ...");
			} catch (InterruptedException e) {
			}
			return " World!";
		});

		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(completableFuture1, completableFuture2,
				completableFuture3);

		try {
			combinedFuture.get();
			
			System.out.println(completableFuture1.get() + completableFuture2.get() + completableFuture3.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
