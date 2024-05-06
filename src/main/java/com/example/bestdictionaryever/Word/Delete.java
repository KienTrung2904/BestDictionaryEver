package com.example.bestdictionaryever.Word;

import com.example.bestdictionaryever.dashboard.search.Trie;
import com.example.bestdictionaryever.dashboard.search.WordHistory;

import java.sql.PreparedStatement;

public class Delete extends Prepare {
    public static boolean delete(String target) {
        int id = getWordId(target);
        if (!checkWordToEdit(id)) {
            return false;
        }

        if (!deleteDefinition(target)) {
            return false;
        }

        if (!deleteWordToEdit(id)) {
            return false;
        }

        if (!deleteWordFromDatabase(target)) {
            return false;
        }

        WordHistory.removeFromHistory(target);
        return true;
    }

    private static boolean deleteWordFromDatabase(String target) {
        final String sql_query = "DELETE FROM triedictionary WHERE word = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);

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
        Trie.deleteWord(target);
        return true;
    }


    private static boolean deleteWordToEdit(int idWord) {
        final String sql_query = "DELETE FROM userdictionary WHERE idWord = ?;";
        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setInt(1, idWord);

            try {
                int row = p.executeUpdate();
                if (row < 0) {
                    return false;
                }
            } finally {
                close(p);
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean deleteDefinition(String target) {
        final String sql_query = "DELETE FROM useredit WHERE word = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);

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
}
