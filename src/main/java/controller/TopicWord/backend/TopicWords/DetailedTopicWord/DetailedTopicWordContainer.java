package controller.TopicWord.backend.TopicWords.DetailedTopicWord;

import java.util.ArrayList;

public class DetailedTopicWordContainer {
    private String topic;
    private ArrayList<DetailedTopicWord> detailedTopicWords;

    public DetailedTopicWordContainer() {

    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<DetailedTopicWord> getDetailedTopicWords() {
        return detailedTopicWords;
    }

    public void setDetailedTopicWords(ArrayList<DetailedTopicWord> detailedTopicWords) {
        this.detailedTopicWords = detailedTopicWords;
    }
}
