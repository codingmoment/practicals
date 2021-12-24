package com.java9.feature;

import java.io.Closeable;
import java.io.IOException;

public class TryWithResourceDemo {

  public static void main(String[] args) throws Exception {
    MyAutoCloseable myAutoCloseable = new MyAutoCloseable();
    
    // You can use the variable defined outside
    try (myAutoCloseable) {
      myAutoCloseable.process();
    }
  }

  private static class MyAutoCloseable implements Closeable {

    public void process() throws Exception {
      throw new Exception();
    }

    @Override
    public void close() throws IOException {
      System.out.println("close() called!");
    }

  }
}
