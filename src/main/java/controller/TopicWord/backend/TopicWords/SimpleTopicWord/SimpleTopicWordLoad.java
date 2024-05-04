package controller.TopicWord.backend.TopicWords.SimpleTopicWord;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SimpleTopicWordLoad {
    public static class SimpleTopicWordContainer {
        private List<SimpleTopicWord> words = new ArrayList<>();

        public SimpleTopicWordContainer() {

        }

        public List<SimpleTopicWord> getWords() {
            return words;
        }

        public void setWords(List<SimpleTopicWord> words) {
            this.words = words;
        }
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String FILE_DIR = "src/main/java/controller/TopicWord/backend/TopicBank/SimpleTopicWords.txt";

    public static ArrayList<SimpleTopicWord> getSimpleTopicWordList() {
        File file = new File(FILE_DIR);
        try {
            SimpleTopicWordContainer container = objectMapper.readValue(file, SimpleTopicWordContainer.class);

            HashSet<SimpleTopicWord> simpleTopicWordSet = new HashSet<>(container.getWords());
            ArrayList<SimpleTopicWord> simpleTopicWordList = new ArrayList<>(simpleTopicWordSet);

            return simpleTopicWordList;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
