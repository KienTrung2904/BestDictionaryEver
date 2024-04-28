package com.example.bestdictionaryever.dashboard.topic;

import com.example.bestdictionaryever.DatabaseConnection;
import javafx.util.Pair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Topic extends DatabaseConnection {
    private String topicName;
    private String topicAvatar;
    private String topicDescription;
    private int topicIndex;
    private static ArrayList<String> topicList = new ArrayList<>();
    private ArrayList<Pair<String, String>> wordList = new ArrayList<>();

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicAvatar() {
        return topicAvatar;
    }

    public void setTopicAvatar(String topicAvatar) {
        this.topicAvatar = topicAvatar;
    }


    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public int getTopicIndex() {
        return topicIndex;
    }

    public void setTopicIndex(int topicIndex) {
        this.topicIndex = topicIndex;
    }


    public void setTopic(String topic) {
        getTopicList();
        setTopicIntroduction(topic);
    }

    public ArrayList<Pair<String, String>> getWordList() {
        return wordList;
    }

    public String getWord(int index) {
        return wordList.get(index).getKey();
    }

    public String getWordImage(int index) {
        return wordList.get(index).getValue();
    }

    public ArrayList<String> getTopicList() {
        final String sql_query = "SELECT topicName from topic";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);

            try {
                ResultSet r = p.executeQuery();

                try {
                    while (r.next()) {
                        topicList.add(r.getString("topicName"));
                    }
                } finally {
                    close(r);
                }
            } finally {
                close(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicList;
    }

    private void setTopicIntroduction(String topic) {
        final String sql_query = "SELECT t.topicIndex, t.topicName, t.topicAvatar, t.topicDescrition, trie.word, t.word_image FROM triedictionary trie INNER JOIN (SELECT * FROM topic NATURAL JOIN moredetailtopic WHERE topic.topicName = ?) t ON trie.word = t.target;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1, topic);

            try {
                ResultSet r = p.executeQuery();
                try {
                    while (r.next()) {
                        setTopicIndex(r.getInt("topicIndex"));
                        setTopicName(r.getString("topicName"));
                        setTopicAvatar( r.getString("topicAvatar"));
                        setTopicDescription(r.getString("topicDescrition"));
                        String word = r.getString("word");
                        String image = r.getString("word_image");
                        System.out.println(image);
                        this.wordList.add(new Pair<String, String>(word, image));
                    }
                } finally {
                    close(r);
                }
            } finally {
                close(p);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
