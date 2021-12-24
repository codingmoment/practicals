package com.java9.feature;

public class InterfacePrivateMethodDemo {

  public static void main(String[] args) throws Exception {
    InterfaceWithPrivateMethods interfaceWithPrivateMethods = new InterfaceWithPrivateMethods() {
    };

    interfaceWithPrivateMethods.check();
  }

  private static interface InterfaceWithPrivateMethods {

    private static String staticPrivate() {
      return "static private";
    }

    private String instancePrivate() {
      return "instance private";
    }

    default void check() {
      String result = staticPrivate();
      System.out.println(result);
      InterfaceWithPrivateMethods pvt = new InterfaceWithPrivateMethods() {
        // anonymous class
      };
      result = pvt.instancePrivate();
      System.out.println(result);
    }
  }
}
