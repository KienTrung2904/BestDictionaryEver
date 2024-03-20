package com.example.bestdictionaryever;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int numOfWords = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numOfWords; i++) {
            String English_word = sc.nextLine();
            String Vietnamese_word = sc.nextLine();
            Dictionary.add(new Word(English_word, Vietnamese_word));
        }
    }

    public static void insertFromFile() {
        try {
            File path = new File("dictionaries.txt");
            Scanner readFile = new Scanner(path);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                int index = line.indexOf("    ");
                String English_word = line.substring(0, index);
                String Vietnamese_word = line.substring(index + 4, line.length());
                Dictionary.add(new Word(English_word, Vietnamese_word));
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static boolean isValidWord(String wordTarget) {
        for (int i = 0; i < wordTarget.length(); i++) {
            if (!isValidCharacter(wordTarget.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidCharacter(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) return true;
        return false;
    }

    public static void Action(int actionNumber) throws IOException {
        if (actionNumber < 0 || actionNumber > 9) {
            System.out.println("Action not supported!");
            return;
        }
        if (actionNumber == 0) {
            return;
        } else if (actionNumber == 1) {
            dictionaryAdd();
        } else if (actionNumber == 2) {
            // remove function
            dictionaryRemove();
        } else if (actionNumber == 3) {
            // update function
            dictionaryUpdate();
        } else if (actionNumber == 4) {
            // display function
            dictionaryDisplay();
        } else if (actionNumber == 5) {
            dictionaryLookUp();
        } else if (actionNumber == 6) {
            // search function
            dictionarySearcher();
        } else if (actionNumber == 7) {
            // game function
            dictionaryGame();
        } else if (actionNumber == 8) {
            // import from file
            dictionaryImportFromFile();
        } else if (actionNumber == 9) {
            // export to file
            dictionaryExportToFile();
        }

    }


    private static void dictionaryAdd() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean checkAddWord = false;
        System.out.println("Nhập từ tiếng Anh bạn muốn thêm: ");
        do {
            String wordAdd = sc.nextLine();
            int index = wordPosition(wordAdd);
            if (index != -1) {
                System.out.println("Từ đã tồn tại!");
                return;
            }
            if (isValidWord(wordAdd)) {
                System.out.println("Nhập nghĩa của từ vừa thêm:");
                String word_explain = sc.nextLine();
                Dictionary.dictionary.add(new Word(wordAdd, word_explain));
                checkAddWord = true;
                FileWriter myWriter = new FileWriter("dictionaries.txt", true);
                myWriter.write(wordAdd + "    " + word_explain);
                System.out.println("Thêm từ thành công!");
                myWriter.close();
            } else {
                System.out.println("Từ không hợp lệ! Vui lòng nhập lại: ");
            }
        } while (!checkAddWord);
    }

    private static void dictionaryRemove() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập từ bạn muốn xóa: ");
        String removeWord = sc.nextLine();
        boolean checkRemoveWord = false;
        int index = wordPosition(removeWord);
        if (index != -1) {
            Dictionary.dictionary.remove(index);
            checkRemoveWord = true;
            System.out.println("Xóa từ thành công!");
        }
        if (!checkRemoveWord) {
            System.out.println("Từ không tồn tại!");
        }
    }

    private static void dictionaryUpdate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập từ bạn muốn cập nhật: ");
        String updateWord = sc.nextLine();
        boolean checkUpdateWord = false;
        int index = wordPosition(updateWord);
        if (index != -1) {
            System.out.print("Đã tìm thấy từ! Nghĩa hiện tại là: ");
            System.out.println(Dictionary.dictionary.get(index).getWord_explain());
            System.out.println("Nhập nghĩa mới của từ: ");
            String word_explain = sc.nextLine();
            Dictionary.dictionary.get(index).setWord_explain(word_explain);
            checkUpdateWord = true;
            System.out.println("Cập nhật từ thành công!");
        }
        if (!checkUpdateWord) {
            System.out.println("Từ không tồn tại!");
        }
    }

    private static void dictionaryDisplay() {
        System.out.println(" No |   English  |   Vietnamese   ");
        for (int i = 0; i < Dictionary.dictionary.size(); i++) {
            String EnglishWord = Dictionary.dictionary.get(i).getWord_target();
            String VietnameseWord = Dictionary.dictionary.get(i).getWord_explain();
            System.out.printf("%03d    | %-10s     | %s \n", i + 1, EnglishWord, VietnameseWord);
        }

    }

    public static void dictionaryLookUp() {
        System.out.println("Nhập từ bạn muốn tra cứu: ");
        Scanner sc = new Scanner(System.in);
        String word_target = sc.nextLine();
        if (!isValidWord(word_target)) {
            System.out.println("Từ không hợp lệ! \n");
        } else {
            boolean found = false;
            for (int i = 0; i < Dictionary.dictionary.size(); i++) {
                if (Dictionary.dictionary.get(i).getWord_target().equals(word_target)) {
                    found = true;
                    System.out.println(word_target + ": " + Dictionary.dictionary.get(i).getWord_explain());
                }
            }
            if (!found) {
                System.out.println("Không tìm thấy từ! \n");
            }
        }
    }

    public static void dictionarySearcher() {
        System.out.println("Nhập cụm muốn tra:");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        System.out.println(" No |   English  |   Vietnamese   ");
        int count = 1;
        for (int i = 0; i < Dictionary.dictionary.size(); i++) {
            if (Dictionary.dictionary.get(i).getWord_target().length() >= word.length()) {
                if (word.equals(Dictionary.dictionary.get(i).getWord_target().substring(0, word.length()))) {
                    String EnglishWord = Dictionary.dictionary.get(i).getWord_target();
                    String VietnameseWord = Dictionary.dictionary.get(i).getWord_explain();
                    System.out.printf("%03d    | %-10s     | %s \n", count++, EnglishWord, VietnameseWord);
                }
            }
        }
    }

    private static void dictionaryGame() {


    }

    private static void dictionaryImportFromFile() {
        System.out.println("Nhập đường dẫn tới file: ");
        Scanner sc = new Scanner(System.in);
        String pathname = sc.nextLine();
        try {
            File path = new File(pathname);
            Scanner readFile = new Scanner(path);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                int index = line.indexOf("    ");
                String English_word = line.substring(0, index);
                String Vietnamese_word = line.substring(index + 4, line.length());
                Dictionary.add(new Word(English_word, Vietnamese_word));
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void dictionaryExportToFile() {
        System.out.println("Nhập tên tệp bạn muốn xuất dữ liệu: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (int i = 0; i < Dictionary.dictionary.size(); i++) {
                myWriter.write(Dictionary.dictionary.get(i).getWord_target()
                        + "    " + Dictionary.dictionary.get(i).getWord_explain() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int wordPosition(String word) {
        for (int i = 0; i < Dictionary.dictionary.size(); i++) {
            if (Dictionary.dictionary.get(i).getWord_target().equals(word)) {
                return i;
            }
        }
        return -1;
    }
}
