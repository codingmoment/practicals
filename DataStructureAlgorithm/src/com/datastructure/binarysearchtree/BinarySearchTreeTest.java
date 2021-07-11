package com.datastructure.binarysearchtree;

public class BinarySearchTreeTest {

  public static void main(String[] args) {
    testDelete();
  }

  public static BinarySearchTree testInsert() {
    BinarySearchTree binarySearchTree = new BinarySearchTree();

    binarySearchTree.insertNode(100);
    binarySearchTree.insertNode(80);
    binarySearchTree.insertNode(200);
    binarySearchTree.insertNode(70);
    binarySearchTree.insertNode(90);
    binarySearchTree.insertNode(150);
    binarySearchTree.insertNode(300);
    binarySearchTree.insertNode(50);
    binarySearchTree.insertNode(250);
    binarySearchTree.insertNode(400);
    binarySearchTree.insertNode(40);
    binarySearchTree.insertNode(60);

    return binarySearchTree;
  }

  public static void testPreOrder() {
    BinarySearchTree binaryTree = testInsert();
    binaryTree.preOrder();
  }

  public static void testInOrder() {
    BinarySearchTree binaryTree = testInsert();
    binaryTree.inOrder();
  }

  public static void testPostOrder() {
    BinarySearchTree binaryTree = testInsert();
    binaryTree.postOrder();
  }

  public static void testLevelOrder() {
    BinarySearchTree binaryTree = testInsert();
    binaryTree.levelOrder();
  }

  public static void testSearch() {
    BinarySearchTree binaryTree = testInsert();
    binaryTree.inOrder();
    boolean searchResult = binaryTree.search(10);
    System.out.println("\nValue 10 exists = " + searchResult);
    searchResult = binaryTree.search(250);
    System.out.println("Value 250 exists = " + searchResult);
    searchResult = binaryTree.search(40);
    System.out.println("Value 40 exists = " + searchResult);
    searchResult = binaryTree.search(400);
    System.out.println("Value 400 exists = " + searchResult);
    searchResult = binaryTree.search(4000);
    System.out.println("Value 4000 exists = " + searchResult);
  }
  
  public static void testDelete() {
    BinarySearchTree binaryTree = testInsert();
    binaryTree.inOrder();
    System.out.println("\nDeleting 10");
    binaryTree.deleteNode(10);
    binaryTree.inOrder();
    System.out.println("\nDeleting 250");
    binaryTree.deleteNode(250);
    binaryTree.inOrder();
    System.out.println("\nDeleting 40");
    binaryTree.deleteNode(40);
    binaryTree.inOrder();
    System.out.println("\nDeleting 400");
    binaryTree.deleteNode(400);
    binaryTree.inOrder();
    System.out.println("\nDeleting 4000");
    binaryTree.deleteNode(4000);
    binaryTree.inOrder();
    System.out.println("\nDeleting All");
    binaryTree.deleteNode(50);
    binaryTree.deleteNode(60);
    binaryTree.deleteNode(70);
    binaryTree.deleteNode(80);
    binaryTree.deleteNode(90);
    binaryTree.deleteNode(100);
    binaryTree.deleteNode(150);
    binaryTree.deleteNode(200);
    binaryTree.deleteNode(300);
    System.out.println("\nResult:");
    binaryTree.inOrder();
  }
}
