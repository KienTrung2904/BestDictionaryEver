package com.example.bestdictionaryever.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class word {
    private String wordTarget;
    private String wordPhonetic;
    private ArrayList<String> wordPartOfSpeech = new ArrayList<>();
    private Map<String, ArrayList<String>> wordExplain = new HashMap<>();
    private boolean addByUser = false;

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordPhonetic() {
        return wordPhonetic;
    }

    public void setWordPhonetic(String wordPhonetic) {
        this.wordPhonetic = wordPhonetic;
    }

    public ArrayList<String> getWordPartOfSpeech() {
        return wordPartOfSpeech;
    }

    public void setWordPartOfSpeech(ArrayList<String> wordPartOfSpeech) {
        this.wordPartOfSpeech = wordPartOfSpeech;
    }

    public Map<String, ArrayList<String>> getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(Map<String, ArrayList<String>> wordExplain) {
        this.wordExplain = wordExplain;
    }

    public void setAddByUser (boolean addByUser) {
        this.addByUser = addByUser;
    }

    public boolean getAddByUser() {
        return addByUser;
    }

    public void addWordExplain(String wordPartOfSpeech, String wordExplain) {
        if (!this.wordExplain.containsKey(wordPartOfSpeech)) {
            this.wordExplain.put(wordPartOfSpeech, new ArrayList<>());
        }
        this.wordExplain.get(wordPartOfSpeech).add(wordExplain);
    }

    public void addWordPartOfSpeech(String partOfSpeech) {
        if (!this.wordExplain.containsKey(partOfSpeech)) {
            this.wordPartOfSpeech.add(partOfSpeech);
        }
    }
}
