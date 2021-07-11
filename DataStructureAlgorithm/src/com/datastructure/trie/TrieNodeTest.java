package com.datastructure.trie;

public class TrieNodeTest {
  public static void main(String[] args) {
    testDelete();
  }

  private static void testInsert() {
    Trie trie = new Trie();
    trie.insert("Nilesh");
    trie.insert("Nil");
    trie.insert("Nik");
    trie.insert("Nimesh");
    trie.insert("Nim");
    trie.insert("Jay");
    trie.insert("Jayesh");
    trie.insert("Jan");

    trie.printAllWords();
  }

  private static void testDelete() {
    Trie trie = new Trie();
    trie.insert("Nilesh");
    trie.insert("Nil");
    trie.insert("Nilkant");
    trie.insert("Nik");
    trie.insert("Nimesh");
    trie.insert("Nim");
    
    System.out.println("Words:");
    trie.printAllWords();
    
    System.out.println("\nDeleting Nilesh");
    trie.delete("Nilesh");
    trie.printAllWords();

    System.out.println("\nDeleting Nilkant");
    trie.delete("Nilkant");
    trie.printAllWords();

    System.out.println("\nDeleting Nimesh");
    trie.delete("Nimesh");
    trie.printAllWords();

    System.out.println("\nDeleting N");
    trie.delete("N");
    trie.printAllWords();

    System.out.println("\nDeleting Test");
    trie.delete("Test");
    trie.printAllWords();

  }
}
