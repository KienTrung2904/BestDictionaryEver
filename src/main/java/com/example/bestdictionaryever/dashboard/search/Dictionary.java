package com.example.bestdictionaryever.dashboard.search;

import com.example.bestdictionaryever.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import static jdk.internal.net.http.common.Utils.close;
/**
 * xay dung cay tu dien Trie
 */
public class Dictionary extends DatabaseConnection {
    private static ArrayList<String> prefixList = new ArrayList<>();
    public static ArrayList<String> getPrefixList() {
        return prefixList;
    }

    public static void lookUpWord(String targetPrefix, int searchLimit) {
        prefixList.clear();
        ArrayList<String> word = Trie.searchWords(targetPrefix, searchLimit);
        for (String s: word) System.out.println(s);
        prefixList.addAll(word);
    }

    public static void trieStruct() {
        final String sql_query = "SELECT word FROM trieDictionary";

        try {
            PreparedStatement ps = databaseLink.prepareStatement(sql_query);

            try {
                ResultSet rs = ps.executeQuery();

                try {
                    while (rs.next()) {
                        String target = rs.getString("word");
                        Trie.insertWord(target);
                    }
                }

                finally {
                    close(rs);
                }

            }

            finally {
                close(ps);
            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
