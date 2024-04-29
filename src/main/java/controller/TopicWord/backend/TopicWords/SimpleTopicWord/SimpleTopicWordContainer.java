package controller.TopicWord.backend.TopicWords.SimpleTopicWord;

import java.util.ArrayList;
import java.util.List;

public class SimpleTopicWordContainer {
    private List<SimpleTopicWord> simpleTopicWords = new ArrayList<>();

    public SimpleTopicWordContainer() {

    }

    public List<SimpleTopicWord> getSimpleTopicWords() {
        return simpleTopicWords;
    }

    public void setSimpleTopicWords(List<SimpleTopicWord> simpleTopicWords) {
        this.simpleTopicWords = simpleTopicWords;
    }
}
