package com.datastructure.binarysearchtree;

public class BSTNode {

  private int value;
  private BSTNode leftNode;
  private BSTNode rightNode;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public BSTNode getLeftNode() {
    return leftNode;
  }

  public void setLeftNode(BSTNode leftNode) {
    this.leftNode = leftNode;
  }

  public BSTNode getRightNode() {
    return rightNode;
  }

  public void setRightNode(BSTNode rightNode) {
    this.rightNode = rightNode;
  }

}
