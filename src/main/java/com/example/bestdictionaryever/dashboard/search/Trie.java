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
        boolean checkEndWord;
        char character;

        private TrieNode(char character) {
            this.character = character;
            this.children = new HashMap<>();
            checkEndWord = false;
        }

    }
    private  final static TrieNode root = new TrieNode('\0');

    private static void dfs(TrieNode current, String prefix, List<String> word, int limit) {
        if (word.size() >= limit) return;

        if (current.checkEndWord) word.add(prefix);

        for (TrieNode child : current.children.values()) {
            dfs(current, prefix + child.character, word, limit);
        }
    }

    public static ArrayList<String> searchWords(String prefix, int limit) {
        ArrayList<String> word = new ArrayList<>();
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) return null;

            current = current.children.get(c);
        }

        dfs(current, prefix, word, limit);
        return word;
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
        current.checkEndWord = true;
    }

    public static  void deleteWord(String word) {
        TrieNode current = root;
        TrieNode preNode = null;

        for (char x : word.toCharArray()) {
            if (!current.children.containsKey(x)) return;
            preNode = current;
            current = current.children.get(x);
        }

        if  (!current.checkEndWord) return;

        if (current.children.isEmpty()) preNode.children.remove(current.character);

        else current.checkEndWord = false;
    }
}
