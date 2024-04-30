package controller.TopicWord.backend.TopicWords.SimpleTopicWord;

import java.util.ArrayList;
import java.util.List;

public class SimpleTopicWordContainer {
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
