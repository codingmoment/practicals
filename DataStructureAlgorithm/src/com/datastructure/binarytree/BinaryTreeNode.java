package com.datastructure.binarytree;

public class BinaryTreeNode {

  private int value;
  private BinaryTreeNode leftNode;
  private BinaryTreeNode rightNode;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public BinaryTreeNode getLeftNode() {
    return leftNode;
  }

  public void setLeftNode(BinaryTreeNode leftNode) {
    this.leftNode = leftNode;
  }

  public BinaryTreeNode getRightNode() {
    return rightNode;
  }

  public void setRightNode(BinaryTreeNode rightNode) {
    this.rightNode = rightNode;
  }

}
