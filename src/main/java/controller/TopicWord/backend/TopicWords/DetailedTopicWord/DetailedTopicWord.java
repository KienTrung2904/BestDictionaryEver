package controller.TopicWord.backend.TopicWords.DetailedTopicWord;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import controller.TopicWord.backend.Utils.Definition;
import controller.TopicWord.backend.Utils.Quiz;

import java.util.Objects;

public class DetailedTopicWord {

    @JsonProperty("type")
    private String type;
    @JsonProperty("definition")
    private Definition definition;

    @JsonProperty("quiz")
    private Quiz quiz;

    public DetailedTopicWord() {

    }

    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailedTopicWord that = (DetailedTopicWord) o;
        return Objects.equals(definition.getTopic(), that.definition.getTopic()) && Objects.equals(this.definition.getWord(), that.getDefinition().getWord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(definition.getTopic(), definition.getWord());
    }

    @Override
    public String toString() {
        return definition + "\n---------------------\n" + quiz;
    }
}
