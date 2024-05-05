package com.example.bestdictionaryever.Word;

import com.example.bestdictionaryever.DatabaseConnection;
import javafx.util.Pair;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Add extends Prepare{
    private static boolean Insert (String target) {
        final String sql_query = "INSERT INTO triedictionary (word) VALUE(?);";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);

            try {
                int row = p.executeUpdate();
                if (row < 0 ) {
                    System.out.println("Error insert");
                    return false;

                }
            } finally {
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error insert1");
            return false;
        }

        return true;
    }

    private static boolean setDictionary(int word_id, String target) {
        final String sql_query = "INSERT INTO userdictionary(accountID, idWord, word) VALUES(?, ?, ?);";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setInt(1, DatabaseConnection.getUser().getId());
            p.setInt(2, word_id);
            p.setString(3, target);

            try {
                int row = p.executeUpdate();
                if ( row < 0) {
                    System.out.println("Error setDictionary");
                    return false;
                }
            } finally {
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error setDictionary1");
            return false;
        }

        return true;
    }

    private static boolean setDefinition(String target, String partOfSpeech, String definition) {
        final String sql_query = "INSERT INTO userEdit(word, partOfSpeech, definition) VALUES (?, ?, ?);";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);
            p.setString(2, partOfSpeech);
            p.setString(3, definition);

            try {
                int row = p.executeUpdate();
                if (row < 0 ) {
                    System.out.println("Error setDefinition");
                    return false;
                }
            } finally {
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error setDefinition1");
            return false;
        }
        return true;
    }


    public static boolean add(String target, ArrayList<Pair<String, String>> definition) {
        if (checkWordExit(target)) {
            return false;
        }
        if (!Insert(target)) {
            return false;
        }

        int id = getWordId(target);

        if (!setDictionary(id, target)) {
            return false;
        }

        for (Pair<String, String> p : definition) {
            String key = p.getKey();
            String value = p.getValue();
            if (!setDefinition(target, key, value)) {
                return false;
            }
        }
        return true;
    }
}