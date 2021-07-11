package com.datastructure.avltree;

public class AVLTreeTest {

  public static void main(String[] args) {
    testDelete();
  }

  public static AVLTree testInsert() {
    AVLTree avlTree = new AVLTree();

    avlTree.insertNode(100);
    avlTree.insertNode(80);
    avlTree.insertNode(200);
    avlTree.insertNode(70);
    avlTree.insertNode(90);
    avlTree.insertNode(150);
    avlTree.insertNode(300);
    avlTree.insertNode(50);
    avlTree.insertNode(250);
    avlTree.insertNode(400);
    avlTree.insertNode(40);
    avlTree.insertNode(60);

    return avlTree;
  }

  public static void testPreOrder() {
    AVLTree avlTree = testInsert();
    avlTree.preOrder();
  }

  public static void testInOrder() {
    AVLTree avlTree = testInsert();
    avlTree.inOrder();
  }

  public static void testPostOrder() {
    AVLTree avlTree = testInsert();
    avlTree.postOrder();
  }

  public static void testLevelOrder() {
    AVLTree avlTree = testInsert();
    avlTree.levelOrder();
  }

  public static void testSearch() {
    AVLTree avlTree = testInsert();
    avlTree.inOrder();
    boolean searchResult = avlTree.search(10);
    System.out.println("\nValue 10 exists = " + searchResult);
    searchResult = avlTree.search(250);
    System.out.println("Value 250 exists = " + searchResult);
    searchResult = avlTree.search(40);
    System.out.println("Value 40 exists = " + searchResult);
    searchResult = avlTree.search(400);
    System.out.println("Value 400 exists = " + searchResult);
    searchResult = avlTree.search(4000);
    System.out.println("Value 4000 exists = " + searchResult);
  }
  
  public static void testDelete() {
    AVLTree avlTree = testInsert();
    avlTree.inOrder();
    System.out.println("\nDeleting 10");
    avlTree.deleteNode(10);
    avlTree.inOrder();
    System.out.println("\nDeleting 250");
    avlTree.deleteNode(250);
    avlTree.inOrder();
    System.out.println("\nDeleting 40");
    avlTree.deleteNode(40);
    avlTree.inOrder();
    System.out.println("\nDeleting 400");
    avlTree.deleteNode(400);
    avlTree.inOrder();
    System.out.println("\nDeleting 4000");
    avlTree.deleteNode(4000);
    avlTree.inOrder();
    System.out.println("\nDeleting All");
    avlTree.deleteNode(50);
    avlTree.deleteNode(60);
    avlTree.deleteNode(70);
    avlTree.deleteNode(80);
    avlTree.deleteNode(90);
    avlTree.deleteNode(100);
    avlTree.deleteNode(150);
    avlTree.deleteNode(200);
    avlTree.deleteNode(300);
    System.out.println("\nResult:");
    avlTree.inOrder();
  }
}
