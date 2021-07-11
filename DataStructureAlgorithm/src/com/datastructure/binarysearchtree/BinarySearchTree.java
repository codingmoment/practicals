package com.datastructure.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

  private BSTNode rootNode;

  public BSTNode getRootNode() {
    return rootNode;
  }

  public void setRootNode(BSTNode rootNode) {
    this.rootNode = rootNode;
  }

  /**
   * Searches the given value into the binary tree
   */
  public boolean search(int value) {
    return search(rootNode, value);
  }

  private boolean search(BSTNode node, int value) {
    if (node == null) {
      return false;
    }

    if (node.getValue() == value) {
      return true;
    }

    if (value < node.getValue()) {
      return search(node.getLeftNode(), value);
    }
    else {
      return search(node.getRightNode(), value);
    }
  }

  /**
   * Traverse the tree using PreOrder
   */
  public void preOrder() {
    preOrder(rootNode);
  }

  private void preOrder(BSTNode node) {
    if (node == null) {
      return;
    }

    System.out.printf("%s ", node.getValue());
    preOrder(node.getLeftNode());
    preOrder(node.getRightNode());
  }

  /**
   * Traverse the tree using InOrder
   */
  public void inOrder() {
    inOrder(rootNode);
  }

  private void inOrder(BSTNode node) {
    if (node == null) {
      return;
    }

    inOrder(node.getLeftNode());
    System.out.printf("%s ", node.getValue());
    inOrder(node.getRightNode());
  }

  /**
   * Traverse the tree using PostOrder
   */
  public void postOrder() {
    postOrder(rootNode);
  }

  private void postOrder(BSTNode node) {
    if (node == null) {
      return;
    }

    postOrder(node.getLeftNode());
    postOrder(node.getRightNode());
    System.out.printf("%s ", node.getValue());
  }

  /**
   * Traverse the tree using LevelOrder
   */
  public void levelOrder() {
    levelOrder(rootNode);
  }

  private void levelOrder(BSTNode node) {
    if (node == null) {
      return;
    }

    Queue<BSTNode> queue = new LinkedList<BSTNode>();
    queue.add(node);

    while (!queue.isEmpty()) {
      BSTNode dequeuedNode = queue.remove();

      System.out.printf("%s ", dequeuedNode.getValue());

      if (dequeuedNode.getLeftNode() != null) {
        queue.add(dequeuedNode.getLeftNode());
      }
      if (dequeuedNode.getRightNode() != null) {
        queue.add(dequeuedNode.getRightNode());
      }
    }
  }

  /**
   * Insert a new node
   */
  public void insertNode(int value) {
    rootNode = insertNode(rootNode, value);
  }

  private BSTNode insertNode(BSTNode currentNode, int value) {
    if (currentNode == null) {
      currentNode = new BSTNode();
      currentNode.setValue(value);
      return currentNode;
    } // If value is less than current node value
    else if (value < currentNode.getValue()) {
      currentNode.setLeftNode(insertNode(currentNode.getLeftNode(), value));
    } // If value is greater than current node value
    else {
      currentNode.setRightNode(insertNode(currentNode.getRightNode(), value));
    }
    return currentNode;
  }

  /**
   * Deletes the node having given value.
   */
  public void deleteNode(int value) {
    rootNode = deleteNode(rootNode, value);
  }

  private BSTNode deleteNode(BSTNode currentNode, int value) {
    if (currentNode == null) {
      return null;
    }

    // If value is less than current node value, then go to the left
    if (value < currentNode.getValue()) {
      currentNode.setLeftNode(deleteNode(currentNode.getLeftNode(), value));
    }
    // If value is greater than current node value, then go to the right
    else if (value > currentNode.getValue()) {
      currentNode.setRightNode(deleteNode(currentNode.getRightNode(), value));
    }
    // If value is equal to current node value
    else {
      // If the current node has no child, remove that child from the previous node
      if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
        currentNode = null;
      }
      // If the current node has only left child
      else if (currentNode.getLeftNode() != null && currentNode.getRightNode() == null) {
        currentNode = currentNode.getLeftNode();
      }
      // If the current node has only right child
      else if (currentNode.getLeftNode() == null && currentNode.getRightNode() != null) {
        currentNode = currentNode.getRightNode();
      }
      // If the current node has both child
      else {
        // Set the value of current node to the lowest node value from the right sub-tree
        currentNode.setValue(getLowestValue(currentNode.getRightNode()));
        // Delete the node from the right sub-tree having lowest value
        currentNode.setRightNode(deleteNode(currentNode.getRightNode(), currentNode.getValue()));
      }
    }

    return currentNode;
  }

  private int getLowestValue(BSTNode node) {
    if (node.getLeftNode() == null) {
      return node.getValue();
    }
    return getLowestValue(node.getLeftNode());
  }
}
