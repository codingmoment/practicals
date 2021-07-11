package com.datastructure.trie;

public class Trie {
  private TrieNode rootNode;

  public TrieNode getRootNode() {
    return rootNode;
  }

  public void setRootNode(TrieNode rootNode) {
    this.rootNode = rootNode;
  }

  /**
   *  Inserts a given word in the Trie 
   */
  public void insert(String word) {
    if (rootNode == null) {
      rootNode = new TrieNode();
    }

    TrieNode currentNode = rootNode;

    for (int i = 0; i < word.length(); i++) {
      TrieNode charNode = currentNode.getCharacters().get(word.charAt(i));

      if (charNode == null) {
        charNode = new TrieNode();
        currentNode.getCharacters().put(word.charAt(i), charNode);
      }

      currentNode = charNode;
    }
    currentNode.setEndOfWord(true);
  }

  /**
   *  Searches for the given word in the Trie. 
   */
  public boolean search(String word) {
    if (rootNode == null)
      return false;

    TrieNode currentNode = rootNode;

    for (int i = 0; i < word.length(); i++) {
      currentNode = currentNode.getCharacters().get(word.charAt(i));

      if (currentNode == null) {
        return false;
      }
    }

    return currentNode.isEndOfWord();
  }
  
  /**
   *  Deletes the given word from the Trie. 
   */
  public void delete(String word) {
    if (search(word) == true) {
      rootNode = delete(rootNode, word, 0);
    }
  }

  private TrieNode delete(TrieNode parentNode, String word, int index) {
    if (index == word.length()) {
      if (parentNode.isEndOfWord()) {
        if (parentNode.getCharacters().isEmpty()) {
          return null;
        }
        else {
          parentNode.setEndOfWord(false);
        }
      }
      return parentNode;
    }

    char ch = word.charAt(index);
    TrieNode deletedNode = delete(parentNode.getCharacters().get(ch), word, index + 1);

    if (deletedNode == null) {
      if(parentNode.isEndOfWord()) {
        return parentNode;
      }
      else {
        parentNode.getCharacters().remove(ch);
        if(parentNode.getCharacters().isEmpty()) {
          return null;
        }
      }
    }

    return parentNode;
  }

  public void printAllWords() {
    if (rootNode == null) {
      System.out.println("Trie is empty!");
      return;
    }

    for (Character c : rootNode.getCharacters().keySet()) {
      getWord(c.toString(), rootNode.getCharacters().get(c));
    }
  }

  private void getWord(String word, TrieNode node) {

    if (node == null) {
      System.out.println(word);
      return;
    }

    if (node.isEndOfWord()) {
      System.out.println(word);
    }

    for (Character c : node.getCharacters().keySet()) {
      getWord(word + c, node.getCharacters().get(c));
    }
  }
}
