package com.example.bestdictionaryever;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandLine {
    public static void showAllWords() {
        System.out.println(" No |   English  |   Vietnamese   ");
        for (int i = 0; i <Dictionary.dictionary.size(); i++) {
            String EnglishWord = Dictionary.dictionary.get(i).getWord_target();
            String VietnameseWord = Dictionary.dictionary.get(i).getWord_explain();
            System.out.printf("%03d    | %-10s     | %s \n", i + 1, EnglishWord, VietnameseWord);
        }
    }
    public static void dictionaryBasis() {
            showAllWords();
    }
    public static void dictionaryAdvanced() throws IOException {
        DictionaryManagement.insertFromFile();
        System.out.println("Welcome to My Application! \n" +
                "[0] Exit \n" +
                "[1] Add \n" +
                "[2] Remove \n" +
                "[3] Update \n" +
                "[4] Display \n" +
                "[5] Lookup \n" +
                "[6] Search \n" +
                "[7] Game \n" +
                "[8] Import from file \n" +
                "[9] Export to file \n");
        Scanner sc = new Scanner(System.in);
        int ActionNumber = 0;
        do {
            System.out.println("Your action: ");
            ActionNumber = sc.nextInt();
            DictionaryManagement.Action(ActionNumber);
        }
        while (ActionNumber != 0);
    }
}
