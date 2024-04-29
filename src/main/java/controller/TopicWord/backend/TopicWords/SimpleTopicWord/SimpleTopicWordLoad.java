package controller.TopicWord.backend.TopicWords.SimpleTopicWord;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class SimpleTopicWordLoad {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String FILE_DIR = "src/main/java/controller/TopicWord/backend/Words/SimpleTopicWords.txt";

    public static ArrayList<SimpleTopicWord> getSimpleTopicWordList() {
        File file = new File(FILE_DIR);
        try {
            SimpleTopicWordContainer container = objectMapper.readValue(file, SimpleTopicWordContainer.class);

            HashSet<SimpleTopicWord> simpleTopicWordSet = new HashSet<>(container.getSimpleTopicWords());
            ArrayList<SimpleTopicWord> simpleTopicWordList = new ArrayList<>(simpleTopicWordSet);

            return simpleTopicWordList;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        ArrayList<SimpleTopicWord> simpleTopicWordList = getSimpleTopicWordList();
        for (SimpleTopicWord word : simpleTopicWordList) {
            System.out.println(word);
            System.out.println();
        };

    }

}
