package com.example.bestdictionaryever;

import java.util.ArrayList;

public class Dictionary {
    public static ArrayList<Word> dictionary = new ArrayList<Word>();
    public static void add(Word word) {
        dictionary.add(word);
    }
}
