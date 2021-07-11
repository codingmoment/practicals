package com.datastructure.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeLinkedList {

  private BinaryTreeNode rootNode;

  public BinaryTreeNode getRootNode() {
    return rootNode;
  }

  public void setRootNode(BinaryTreeNode rootNode) {
    this.rootNode = rootNode;
  }

  /**
   * Insert a new node at the first vacant place as per level order traversal.
   */
  public void insertNode(int value) {

    // If tree is empty, we create root node.
    if (rootNode == null) {
      rootNode = new BinaryTreeNode();
      rootNode.setValue(value);
      return;
    }

    BinaryTreeNode newNode = new BinaryTreeNode();
    newNode.setValue(value);

    Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
    queue.add(rootNode);

    while (!queue.isEmpty()) {
      BinaryTreeNode dequeuedNode = queue.remove();

      if (dequeuedNode.getLeftNode() == null) {
        dequeuedNode.setLeftNode(newNode);
        return;
      }
      if (dequeuedNode.getRightNode() == null) {
        dequeuedNode.setRightNode(newNode);
        return;
      }

      queue.add(dequeuedNode.getLeftNode());
      queue.add(dequeuedNode.getRightNode());
    }
  }

  /**
   * Traverse the tree using PreOrder
   */
  public void preOrder() {
    preOrder(rootNode);
  }
  
  private void preOrder(BinaryTreeNode node) {
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
  
  private void inOrder(BinaryTreeNode node) {
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
  
  private void postOrder(BinaryTreeNode node) {
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
  
  private void levelOrder(BinaryTreeNode node) {
    if (node == null) {
      return;
    }

    Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
    queue.add(node);

    while (!queue.isEmpty()) {
      BinaryTreeNode dequeuedNode = queue.remove();

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
   * Searches the given value into the binary tree
   */
  public boolean search(int value) {
    if (rootNode == null) {
      return false;
    }

    Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
    queue.add(rootNode);

    while (!queue.isEmpty()) {
      BinaryTreeNode dequeuedNode = queue.remove();

      if (dequeuedNode.getValue() == value) {
        return true;
      }

      if (dequeuedNode.getLeftNode() != null) {
        queue.add(dequeuedNode.getLeftNode());
      }
      if (dequeuedNode.getRightNode() != null) {
        queue.add(dequeuedNode.getRightNode());
      }
    }

    return false;
  }

  /**
   * Deletes the given value from the binary tree
   */
  public void deleteNode(int value) {
    if (rootNode == null) {
      return;
    }

    Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
    queue.add(rootNode);

    while (!queue.isEmpty()) {
      BinaryTreeNode dequeuedNode = queue.remove();

      if (dequeuedNode.getValue() == value) {
        // Get the deepest node value
        BinaryTreeNode deepestNode = getDeepestNode();
        // Set the value of current node to the deepest node value
        dequeuedNode.setValue(deepestNode.getValue());
        // Delete the deepest node
        deleteDeepestNode();
        return;
      }

      if (dequeuedNode.getLeftNode() != null) {
        queue.add(dequeuedNode.getLeftNode());
      }
      if (dequeuedNode.getRightNode() != null) {
        queue.add(dequeuedNode.getRightNode());
      }
    }
  }

  /**
   * Gets the deepest tree node
   */
  private BinaryTreeNode getDeepestNode() {
    if (rootNode == null) {
      return null;
    }

    Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
    queue.add(rootNode);

    BinaryTreeNode lastDequeuedNode = null;

    while (!queue.isEmpty()) {
      lastDequeuedNode = queue.remove();

      if (lastDequeuedNode.getLeftNode() != null) {
        queue.add(lastDequeuedNode.getLeftNode());
      }
      if (lastDequeuedNode.getRightNode() != null) {
        queue.add(lastDequeuedNode.getRightNode());
      }
    }
    return lastDequeuedNode;
  }

  /**
   * Deletes the deepest node
   */
  private void deleteDeepestNode() {
    if (rootNode == null) {
      return;
    }

    Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
    queue.add(rootNode);

    BinaryTreeNode previousNode = null, presentNode = null;

    while (!queue.isEmpty()) {
      previousNode = presentNode;
      presentNode = queue.remove();

      if (presentNode.getLeftNode() == null) {
        previousNode.setRightNode(null);
        return;
      }

      if (presentNode.getRightNode() == null) {
        presentNode.setLeftNode(null);
        return;
      }

      if (presentNode.getLeftNode() != null) {
        queue.add(presentNode.getLeftNode());
      }
      if (presentNode.getRightNode() != null) {
        queue.add(presentNode.getRightNode());
      }

    }

  }
}
