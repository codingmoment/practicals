package com.cloning;

/**
 * Demo class to see circular references result in infinite loop during deep cloning.
 * 
 */
public class CircularInfiniteDeepCloneDemo {

  public static void main(String[] args) throws CloneNotSupportedException {
    Parent parent = new Parent();
    Child child = new Child();
    parent.setChild(child);
    child.setParent(parent);

    //Below line should result into infinite loop
    parent.clone();
  }

  private static class Parent implements Cloneable {
    private Child child;

    public Child getChild() {
      return child;
    }

    public void setChild(Child child) {
      this.child = child;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
      Parent clonedParent = (Parent) super.clone();
      Child clonedChild = (Child) clonedParent.getChild().clone();

      clonedParent.setChild(clonedChild);
      return clonedParent;
    }

  }

  private static class Child implements Cloneable {
    private Parent parent;

    public Parent getParent() {
      return parent;
    }

    public void setParent(Parent parent) {
      this.parent = parent;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
      Child clonedChild = (Child) super.clone();
      Parent clonedParent = (Parent) clonedChild.getParent().clone();

      clonedChild.setParent(clonedParent);
      return clonedChild;
    }
  }
}
