package com.datastructure.avltree;

public class AVLTreeNode {

  private int value;
  private int height;
  private AVLTreeNode leftNode;
  private AVLTreeNode rightNode;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public AVLTreeNode getLeftNode() {
    return leftNode;
  }

  public void setLeftNode(AVLTreeNode leftNode) {
    this.leftNode = leftNode;
  }

  public AVLTreeNode getRightNode() {
    return rightNode;
  }

  public void setRightNode(AVLTreeNode rightNode) {
    this.rightNode = rightNode;
  }

}
