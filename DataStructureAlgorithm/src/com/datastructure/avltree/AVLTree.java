package com.datastructure.avltree;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {

  private AVLTreeNode rootNode;

  public AVLTreeNode getRootNode() {
    return rootNode;
  }

  public void setRootNode(AVLTreeNode rootNode) {
    this.rootNode = rootNode;
  }

  /**
   * Searches the given value into the binary tree
   */
  public boolean search(int value) {
    return search(rootNode, value);
  }

  private boolean search(AVLTreeNode node, int value) {
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

  private void preOrder(AVLTreeNode node) {
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

  private void inOrder(AVLTreeNode node) {
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

  private void postOrder(AVLTreeNode node) {
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

  private void levelOrder(AVLTreeNode node) {
    if (node == null) {
      return;
    }

    Queue<AVLTreeNode> queue = new LinkedList<AVLTreeNode>();
    queue.add(node);

    while (!queue.isEmpty()) {
      AVLTreeNode dequeuedNode = queue.remove();

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

  private AVLTreeNode insertNode(AVLTreeNode currentNode, int value) {

    // Same as binary search tree
    if (currentNode == null) {
      currentNode = new AVLTreeNode();
      currentNode.setValue(value);
      return currentNode;
    } // If value is less than current node value
    else if (value < currentNode.getValue()) {
      currentNode.setLeftNode(insertNode(currentNode.getLeftNode(), value));
    } // If value is greater than current node value
    else {
      currentNode.setRightNode(insertNode(currentNode.getRightNode(), value));
    }
    // Same as binary search tree

    // Doing actual AVL tree operations
    // Is the height difference between left and right node is more than 1
    int heightDifference = getHeightDifference(currentNode.getLeftNode(), currentNode.getRightNode());

    // Left sub-tree is overloaded
    if (heightDifference > 1) {
      // Which grand-child from left sub-tree has maximum height
      if (getHeightDifference(currentNode.getLeftNode().getLeftNode(), currentNode.getLeftNode().getRightNode()) > 0) {
        // Left grand-child has more height
        // Left-Left Condition --> rightRotate
        currentNode = rightRotate(currentNode);
      }
      else {
        // Right grand-child has more height
        // Left-Right Condition --> leftRotate & rightRotate
        currentNode.setLeftNode(leftRotate(currentNode.getLeftNode()));
        currentNode = rightRotate(currentNode);
      }
    } // Right sub-tree is overloaded
    else if (heightDifference < -1) {
      // Which grand-child from right sub-tree has maximum height
      if (getHeightDifference(currentNode.getRightNode().getRightNode(), currentNode.getRightNode().getLeftNode()) > 0) {
        // Right grand-child has more height
        // Right-Right Condition --> leftRotate
        currentNode = leftRotate(currentNode);
      }
      else {
        // Left grand-child has more height
        // Right-Left Condition --> rightRotate & leftRotate
        currentNode.setRightNode(rightRotate(currentNode.getRightNode()));
        currentNode = leftRotate(currentNode);
      }
    }

    if (currentNode.getLeftNode() != null) {
      currentNode.getLeftNode().setHeight(calculateHeight(currentNode.getLeftNode()));
    }
    if (currentNode.getRightNode() != null) {
      currentNode.getRightNode().setHeight(calculateHeight(currentNode.getRightNode()));
    }
    currentNode.setHeight(calculateHeight(currentNode));

    return currentNode;
  }

  private AVLTreeNode leftRotate(AVLTreeNode node) {
    AVLTreeNode rightChildNode = node.getRightNode();
    node.setRightNode(rightChildNode.getLeftNode());
    rightChildNode.setLeftNode(node);

    node.setHeight(calculateHeight(node));
    rightChildNode.setHeight(calculateHeight(rightChildNode));

    return rightChildNode;
  }

  private AVLTreeNode rightRotate(AVLTreeNode node) {
    AVLTreeNode leftChildNode = node.getLeftNode();
    node.setLeftNode(leftChildNode.getRightNode());
    leftChildNode.setRightNode(node);

    node.setHeight(calculateHeight(node));
    leftChildNode.setHeight(calculateHeight(leftChildNode));

    return leftChildNode;
  }

  private int getHeightDifference(AVLTreeNode leftNode, AVLTreeNode rightNode) {
    int leftHeight = leftNode == null ? -1 : leftNode.getHeight();
    int rightHeight = rightNode == null ? -1 : rightNode.getHeight();
    return leftHeight - rightHeight;
  }

  private int calculateHeight(AVLTreeNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(node.getLeftNode() == null ? -1 : node.getLeftNode().getHeight(), node.getRightNode() == null ? -1 : node.getRightNode().getHeight()) + 1;
  }

  /**
   * Deletes the node having given value.
   */
  public void deleteNode(int value) {
    rootNode = deleteNode(rootNode, value);
  }

  private AVLTreeNode deleteNode(AVLTreeNode currentNode, int value) {
    // Same as binary search tree
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
      
      return currentNode;
    }
    // Same as binary search tree

    // Doing actual AVL tree operations
    // Is the height difference between left and right node is more than 1
    int heightDifference = getHeightDifference(currentNode.getLeftNode(), currentNode.getRightNode());

    // Left sub-tree is overloaded
    if (heightDifference > 1) {
      // Which grand-child from left sub-tree has maximum height
      if (getHeightDifference(currentNode.getLeftNode().getLeftNode(), currentNode.getLeftNode().getRightNode()) > 0) {
        // Left grand-child has more height
        // Left-Left Condition --> rightRotate
        currentNode = rightRotate(currentNode);
      }
      else {
        // Right grand-child has more height
        // Left-Right Condition --> leftRotate & rightRotate
        currentNode.setLeftNode(leftRotate(currentNode.getLeftNode()));
        currentNode = rightRotate(currentNode);
      }
    } // Right sub-tree is overloaded
    else if (heightDifference < -1) {
      // Which grand-child from right sub-tree has maximum height
      if (getHeightDifference(currentNode.getRightNode().getRightNode(), currentNode.getRightNode().getLeftNode()) > 0) {
        // Right grand-child has more height
        // Right-Right Condition --> leftRotate
        currentNode = leftRotate(currentNode);
      }
      else {
        // Left grand-child has more height
        // Right-Left Condition --> rightRotate & leftRotate
        currentNode.setRightNode(rightRotate(currentNode.getRightNode()));
        currentNode = leftRotate(currentNode);
      }
    }

    if (currentNode.getLeftNode() != null) {
      currentNode.getLeftNode().setHeight(calculateHeight(currentNode.getLeftNode()));
    }
    if (currentNode.getRightNode() != null) {
      currentNode.getRightNode().setHeight(calculateHeight(currentNode.getRightNode()));
    }
    currentNode.setHeight(calculateHeight(currentNode));
    
    return currentNode;
  }

  private int getLowestValue(AVLTreeNode node) {
    if (node.getLeftNode() == null) {
      return node.getValue();
    }
    return getLowestValue(node.getLeftNode());
  }
}
