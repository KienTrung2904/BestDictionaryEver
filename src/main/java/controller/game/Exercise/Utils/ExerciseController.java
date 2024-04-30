package controller.game.Exercise.Utils;

import controller.ScreenControl;
import controller.game.backend.Exercises.MultipleChoice.MultipleChoice;
import controller.game.backend.Utils.Exercise;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class ExerciseController<T extends Exercise> extends ScreenControl {
    public void backToChooseGame() {
        super.chooseGame();
    }

    public static final int COEFFICIENT = 100;
    protected int score;

    protected int health = 3;

    protected int totalQuestions;

    protected T currentExercise;
    protected int questionIndex;
    @FXML
    protected ImageView blood1, blood2, blood3;

    protected String userAnswer;
    @FXML
    protected Label question, scoreLabel, questionIndexLabel;
    protected ArrayList<T> exerciseList;

    public void getExerciseList() {
        exerciseList = new ArrayList<>();
    }

    public void setExerciseList(ArrayList<T> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public abstract void setNextQuestion();

    public void checkAnswer(Button option) throws InterruptedException {
        System.out.println("UserAnswer: " + userAnswer + " -- CorrectAnswer: " + currentExercise.getCorrectAnswer());
        if (userAnswer.equals(currentExercise.getCorrectAnswer())) {
            // set background color of button chosen to green in 2 seconds
            score++;
        } else {
            System.out.println("Incorrect");
            health--;
        }
        userAnswer = null;
        questionIndex++;
        setNextQuestion();
    }



}
