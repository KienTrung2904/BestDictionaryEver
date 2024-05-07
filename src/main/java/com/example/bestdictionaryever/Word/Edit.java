package com.example.bestdictionaryever.Word;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Edit extends Prepare{
    public static boolean saveDefinition(String target, String oldPartOfSpeech, String newPartOfSpeech, String oldDefinition, String newDefinition) {
        int id = getWordId(target);
        if (!checkWordToEdit(id)) {
            return false;
        }

        if(isSave(target, oldPartOfSpeech, oldDefinition)) {
            return editDefinition(target, oldPartOfSpeech, oldDefinition, newPartOfSpeech, newDefinition);
        } else {
            return addDefinition(target, newPartOfSpeech, newDefinition);
        }
    }

    private static boolean editDefinition(String target, String oldPartOfSpeech, String oldDefinition, String newPartOfSpeech, String newDefinition) {
        final String sql_query = "UPDATE useredit SET partOfSpeech = ?, definition = ? WHERE word = ? AND partOfSpeech = ? AND definition = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, newPartOfSpeech);
            p.setString(2, newDefinition);
            p.setString(3, target);
            p.setString(4, oldPartOfSpeech);
            p.setString(5, oldDefinition);

            try {
                int r = p.executeUpdate();
                if (r < 0) return false;
            } finally {
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean addDefinition(String target, String newPartOfSpeech, String newDefinition) {
        int id = getWordId(target);
        if (checkWordToEdit(id)) return false;
        final String sql_query = "INSERT INTO useredit(word, partOfSpeech, definition) VALUES(?, ?, ?);";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);
            p.setString(2, newPartOfSpeech);
            p.setString(3, newDefinition);

            try {
                int row = p.executeUpdate();
                if (row < 0) return false;
            } finally {
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean deleteDefinition(String target, String partOfSpeech, String definition) {
        int id = getWordId(target);
        if (checkWordToEdit(id)) return false;

        final String sql_query = "DELETE FROM useredit WHERE word = ? AND partOfSpeech = ? AND definition = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);
            p.setString(2, partOfSpeech);
            p.setString(3, definition);

            try {
                int row = p.executeUpdate();
                if (row < 0) return false;
            } finally {
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean isSave(String target, String oldPartOfSpeech, String oldDefinition) {
        final String sql_query = "SELECT * FROM useredit WHERE word = ? AND partOfSpeech = ? AND definition = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);
            p.setString(2, oldPartOfSpeech);
            p.setString(3, oldDefinition);

            try {
                ResultSet r = p.executeQuery();
                try {
                    return r.next();
                } finally {
                    close(r);
                }
            } finally {
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
