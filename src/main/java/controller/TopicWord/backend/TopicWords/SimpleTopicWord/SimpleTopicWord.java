package controller.TopicWord.backend.TopicWords.SimpleTopicWord;

import java.util.Objects;

public class SimpleTopicWord {
    private String topic;

    private String word;

    public SimpleTopicWord() {
    }

    public SimpleTopicWord(String topic, String word) {
        this.topic = topic;
        this.word = word;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleTopicWord that = (SimpleTopicWord) o;
        return Objects.equals(topic, that.topic) && Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, word);
    }

    @Override
    public String toString() {
        return "SimpleTopicWord{" +
                "topic='" + topic + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}
