package com.multithread.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This program demonstrates how you can use ThreadLocal to associate state to a thread.
 * 
 * @author N.Wani
 *
 */
public class ThreadLocalState {
  
  private static final ThreadLocal<String> threadName = new ThreadLocal<>();

  public static void main(String[] args) throws Exception {

    List<CompletableFuture<Void>> futureList = new ArrayList<>();

    for (int i = 0; i < 5; i++) {

      final int counter = i;

      CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
        threadName.set(" (Counter_" + counter + ")");
        
        System.out.println(Thread.currentThread().getName() + " started - " + counter + threadName.get());
        
        try {
          Thread.sleep(2000);

          new Thread(new MyRunnable(threadName.get())).start();
          
          System.out.println(Thread.currentThread().getName() + " completed " + counter + threadName.get());
        }
        catch (InterruptedException e) {
        }
      });

      futureList.add(completableFuture);
    }

    CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));

    combinedFuture.get();
  }
  
  static class MyRunnable implements Runnable {
    
    private String counter;
    
    public MyRunnable(String counter) {
      this.counter = counter;
    }

    @Override
    public void run() {
      System.out.println("MyRunnable - " + Thread.currentThread().getName() + " - counter = " + counter + " thread-local varaible = " + threadName.get());
    }
    
  }
}
