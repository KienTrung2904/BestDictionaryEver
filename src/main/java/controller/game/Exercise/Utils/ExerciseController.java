package controller.game.Exercise.Utils;

import controller.ScreenControl;
import controller.game.backend.Exercises.MultipleChoice.MultipleChoice;
import controller.game.backend.Utils.Exercise;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class ExerciseController<T extends Exercise> extends ScreenControl {
    public void backToChooseGame() {
        super.chooseGame();
    }

    protected T currentExercise;
    protected int questionIndex;

    protected String userAnswer;
    @FXML
    protected Label question;
    protected ArrayList<T> exerciseList;

    public void getExerciseList() {
        exerciseList = new ArrayList<>();
    }

    public void setExerciseList(ArrayList<T> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public abstract void setNextQuestion();

    public void checkAnswer() {
        if (currentExercise instanceof MultipleChoice) {

        }
        System.out.println("UserAnswer: " + userAnswer + " -- CorrectAnswer: " + currentExercise.getCorrectAnswer());
        if (userAnswer.equals(currentExercise.getCorrectAnswer())) {
            System.out.println("Correct");
        } else {
            System.out.println("Incorrect");
        }
        userAnswer = null;
        questionIndex++;
        setNextQuestion();
    }


}
