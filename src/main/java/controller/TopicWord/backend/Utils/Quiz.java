package controller.TopicWord.backend.Utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import controller.game.backend.Utils.Exercise;

public class Quiz {

    @JsonProperty("exerciseType")
    private String exerciseType;

    @JsonProperty("description")
    private Description description;

    @JsonIgnore
    private Exercise exercise;

    public Quiz() {

    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public String toString() {
        return "exerciseType: " + exerciseType + "\n" + description;
    }
}
