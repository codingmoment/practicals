package com.datastructure.binarytree;

public class BinaryTreeLinkedListTest {

  public static void main(String[] args) {
    testDelete();
  }

  public static BinaryTreeLinkedList testInsert() {
    BinaryTreeLinkedList binaryTree = new BinaryTreeLinkedList();

    binaryTree.insertNode(20);
    binaryTree.insertNode(100);
    binaryTree.insertNode(3);
    binaryTree.insertNode(50);
    binaryTree.insertNode(15);
    binaryTree.insertNode(250);
    binaryTree.insertNode(35);
    binaryTree.insertNode(222);

    return binaryTree;
  }

  public static void testPreOrder() {
    BinaryTreeLinkedList binaryTree = testInsert();
    binaryTree.preOrder();
  }

  public static void testInOrder() {
    BinaryTreeLinkedList binaryTree = testInsert();
    binaryTree.inOrder();
  }

  public static void testPostOrder() {
    BinaryTreeLinkedList binaryTree = testInsert();
    binaryTree.postOrder();
  }

  public static void testLevelOrder() {
    BinaryTreeLinkedList binaryTree = testInsert();
    binaryTree.levelOrder();
  }

  public static void testSearch() {
    BinaryTreeLinkedList binaryTree = testInsert();
    boolean searchResult = binaryTree.search(10);
    System.out.println("Value 10 exists = " + searchResult);
    searchResult = binaryTree.search(250);
    System.out.println("Value 250 exists = " + searchResult);
  }

  public static void testDelete() {
    System.out.println("After deleting 10:");
    BinaryTreeLinkedList binaryTree = testInsert();
    binaryTree.deleteNode(10);
    binaryTree.levelOrder();
    System.out.println("\nAfter deleting 20:");
    binaryTree = testInsert();
    binaryTree.deleteNode(20);
    binaryTree.levelOrder();
    System.out.println("\nAfter deleting 3:");
    binaryTree = testInsert();
    binaryTree.deleteNode(3);
    binaryTree.levelOrder();
    System.out.println("\nAfter deleting 222:");
    binaryTree = testInsert();
    binaryTree.deleteNode(222);
    binaryTree.levelOrder();
  }

}
