package com.example.bestdictionaryever.Word;

import com.example.bestdictionaryever.DatabaseConnection;
import com.example.bestdictionaryever.dictionary.word;
import javafx.util.Pair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Prepare extends DatabaseConnection {

    public static int getWordId(String target) {
        final String sql_query = "SELECT id FROM triedictionary WHERE word = ?;";
        int id = -1;
        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);

            try {
                ResultSet r = p.executeQuery();

                try {
                    if (r.next()) {
                        id = r.getInt("id");
                    }
                } finally {
                    close(r);
                }
            } finally {
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    public static boolean checkWordToEdit(int id_word) {
        final String sql_query = "SELECT * FROM userdictionary WHERE accountID = ? AND idWord = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setInt(1, user.getId());
            p.setInt(2, id_word);

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

        return false;
    }

    public static boolean checkWordExit(String target) {
        final String sql_query = "SELECT word FROM triedictionary WHERE word = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);

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

    public static ArrayList<Pair<String, String>> lookUpUserWord(word word) {
        String target = word.getWordTarget();
        ArrayList<Pair<String, String>> res = new ArrayList<>();
        final String sql_query = "SELECT * FROM useredit WHERE word = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);

            try {
                ResultSet r = p.executeQuery();

                try {
                    while (r.next()) {
                        String partOfSpeech = r.getString("partOfSpeech");
                        String definition = r.getString("definition");
                        String[] explain = definition.split("\n");
                        word.addWordPartOfSpeech(partOfSpeech);
                        for (String s : explain) {
                            word.addWordExplain(partOfSpeech, s);
                        }
                    }
                } finally {
                    close(r);
                }
            } finally{
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static boolean isUserWord(String target) {
        final String sql_query = "SELECT * FROM userdictionary WHERE word = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, target);

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

        return false;
    }

    public static boolean getUserEdit(word word) {
        String target = word.getWordTarget();
        final String sql_query = "SELECT partOfSpeech, definition FROM userdictionary NATURAL JOIN userEdit WHERE accountID = ? AND word = ?;" ;

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setInt(1, DatabaseConnection.getUser().getId());
            p.setString(2, target);

            try {
                ResultSet r = p.executeQuery();

                try {
                    while (r.next()) {
                        String partOfSpeech = r.getString("partOfSpeech");
                        String definition = r.getString("definition");
                        String[] explain = definition.split("\n");
                        word.addWordPartOfSpeech(partOfSpeech);
                        for (String s : explain) {
                            word.addWordExplain(partOfSpeech, s);
                        }
                    }
                    return true;
                } finally {
                    close(r);
                }
            } finally {
                close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
