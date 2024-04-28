package com.example.bestdictionaryever.dashboard.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * search speed
 */
public class Trie {
    private  static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndCharacterOfWord;
        char character;

        private TrieNode(char character) {
            this.character = character;
            this.children = new HashMap<>();
            isEndCharacterOfWord = false;
        }

    }
    private  final static TrieNode root = new TrieNode('\0');

    private static void dfs(TrieNode node, String word, List<String> matches, int limit) {
        if (matches.size() >= limit) return;

        if (node.isEndCharacterOfWord) matches.add(word);

        for (TrieNode child : node.children.values()) {
            dfs(child, word + child.character, matches, limit);
        }
    }

    public static ArrayList<String> searchWords(String prefix, int limit) {
        ArrayList<String> matches = new ArrayList<>();
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) return null;

            current = current.children.get(c);
        }

        dfs(current, prefix, matches, limit);
        return matches;
    }

    public static void insertWord(String word) {
        TrieNode current = root;

        for ( char c : word.toCharArray()) {
            TrieNode node =  current.children.get(c);
            if (node == null) {
                node = new TrieNode(c);
                current.children.put(c,node);
            }
            current = node;
        }
        current.isEndCharacterOfWord = true;
    }

    public static  void deleteWord(String word) {
        TrieNode current = root;
        TrieNode preNode = null;

        for (char x : word.toCharArray()) {
            if (!current.children.containsKey(x)) return;
            preNode = current;
            current = current.children.get(x);
        }

        if  (!current.isEndCharacterOfWord) return;

        if (current.children.isEmpty()) preNode.children.remove(current.character);

        else current.isEndCharacterOfWord = false;
    }
}
