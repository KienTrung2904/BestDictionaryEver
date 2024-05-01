package controller.game.Exercise.Utils;

import controller.ScreenControl;
import controller.game.Exercise.Exercise.MultipleChoiceController;
import controller.game.backend.Utils.Exercise;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;

public abstract class ExerciseController<T extends Exercise> extends ScreenControl {

    public static final int COEFFICIENT = 100;

    public static final int QUESTIONNUMBER = 20;
    protected int score;

    protected int health = 3;

    protected int totalQuestions;

    protected T currentExercise;
    protected int questionIndex;
    @FXML
    protected ImageView blood1, blood2, blood3;

    protected String userAnswer, correctAnswer;
    @FXML
    protected Label question, scoreLabel, highestScoreLabel, questionIndexLabel;
    protected ArrayList<T> exerciseList;

    public void randomizeExerciseList() {
        ArrayList<T> temp = new ArrayList<>(exerciseList);
        Collections.shuffle(temp);
        if (temp.size() > QUESTIONNUMBER) {
            exerciseList = new ArrayList<>(temp.subList(0, QUESTIONNUMBER));
        } else {
            exerciseList = temp;
        }
    }

    public void getExerciseList() {
        exerciseList = new ArrayList<>();
    }

    public void setExerciseList(ArrayList<T> exerciseList) {
        this.exerciseList = exerciseList;
    }

    private MediaPlayer correctMediaPlayer = new MediaPlayer(new Media(getClass().getResource("/com/example/bestdictionaryever/Game/Sound/correct.mp3").toString()));
    private MediaPlayer incorrectMediaPlayer = new MediaPlayer(new Media(getClass().getResource("/com/example/bestdictionaryever/Game/Sound/incorrect.mp3").toString()));
    private MediaPlayer congratulationsMediaPlayer = new MediaPlayer(new Media(getClass().getResource("/com/example/bestdictionaryever/Game/Sound/congratulation.mp3").toString()));


    public abstract void setNextQuestion();

    public void checkAnswer(Button option, String explaination) throws InterruptedException {
        System.out.println("UserAnswer: " + userAnswer + " -- CorrectAnswer: " + currentExercise.getCorrectAnswer());
        if (userAnswer.equals(correctAnswer)) {
            playEffect(correctMediaPlayer);
            score++;
            scoreLabel.setText("Score: " + score);
            if (option != null) option.getStyleClass().add("correctOption");
            alertInformation("Correct", explaination).showAndWait();
        } else {
            playEffect(incorrectMediaPlayer);
            if (option != null) option.getStyleClass().add("incorrectOption");
            showHealth(health--);
            alertInformation("Incorrect", explaination).showAndWait();
            if (health == -1) {
                alertInformation("Game Over", "\"Game Over\",You have lost all your turn").showAndWait();
            }

        }
        userAnswer = null;
        questionIndex++;
        if (questionIndex == QUESTIONNUMBER) {
            alertInformation("Congratulations", "Congratulations! You're actually excellent!").showAndWait();
        }
        setNextQuestion();
    }

    private void showHealth(int healt) {
        if (health == 2) {
            blood3.setVisible(false);
        } else if (health == 1) {
            blood2.setVisible(false);
        } else if (health == 0) {
            blood1.setVisible(false);
        }
    }

    public void showScore_Ques() {
        scoreLabel.setText("Score: " + score);
        questionIndexLabel.setText("Question: " + (questionIndex + 1) + "/" + totalQuestions);
        showHighestScore();

    }

    public abstract void showHighestScore();


    public Alert alertInformation(String title, String content) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setPrefWidth(500);
        alert.getDialogPane().setPrefHeight(200);
        alert.setTitle(null);
        alert.setHeaderText(null);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setHeaderText(null);
        if (title.equals("Congratulations")) {
            content = "Congratulations! You're actually excellent!";
            //dialogPane.getStyleClass().add("congratulationsBackground");
        }
        if (title.equals("Game Over")) {
            content = "Game Over! You have lost all your turn!";
        } else
        if (title.equals("Correct")) {
            content = "Your answer is correct! You got a new point!\n Explanation:\n" + content;
            //dialogPane.getStyleClass().add("correctBackground");
        } else {
            content = "Your answer is incorrect! The correct answer is: " + currentExercise.getCorrectAnswer() + "\n" + content;
            //dialogPane.getStyleClass().add("incorrectBackground");
        }
        Label contentLabel = new Label(content);

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(contentLabel);
        dialogPane.setContent(hbox);

        contentLabel.setWrapText(true);
        contentLabel.setAlignment(Pos.CENTER_LEFT);

        contentLabel.setMaxWidth(Double.MAX_VALUE);  // Cho phép label mở rộng tối đa theo chiều ngang của HBox
        HBox.setHgrow(contentLabel, Priority.ALWAYS);

        dialogPane.getStylesheets().add(getClass().getResource("/com/example/bestdictionaryever/Game/CSS/Alert.css").toExternalForm());
        contentLabel.getStyleClass().add("content-label");

        // alignt text to center
        return alert;
    }

    protected void stopAllEffects() {
        correctMediaPlayer.stop();
        incorrectMediaPlayer.stop();
    }

    protected void playEffect(MediaPlayer player) {
        stopAllEffects();
        player.play();
    }

    protected void playCorrectEffect() {
        playEffect(correctMediaPlayer);
    }

    protected void playIncorrectEffect() {
        playEffect(incorrectMediaPlayer);
    }

    public void UpdateScore(String column, int newScore) {
        System.out.println("Going to update Score = " + newScore + " for column = " + column);
        user.updateScoreTopic(column, newScore);
    }

    public int getHighestScore(String column) {
        return user.getHighestScore(column);
    }
    public void backToChooseGame() {
        super.chooseGame();
    }

}
