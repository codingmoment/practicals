package com.datastructure.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
  private Map<Character, TrieNode> characters;
  private boolean endOfWord;
  
  public TrieNode() {
    characters = new HashMap<Character, TrieNode>();
    endOfWord = false;
  }

  public Map<Character, TrieNode> getCharacters() {
    return characters;
  }

  public void setCharacters(Map<Character, TrieNode> characters) {
    this.characters = characters;
  }

  public boolean isEndOfWord() {
    return endOfWord;
  }

  public void setEndOfWord(boolean endOfWord) {
    this.endOfWord = endOfWord;
  }
  
  @Override
  public String toString() {
    return characters.keySet().toString();
  }
}
