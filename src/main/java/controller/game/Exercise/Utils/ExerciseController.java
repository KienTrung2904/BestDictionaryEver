package controller.game.Exercise.Utils;

import controller.ScreenControl;
import controller.game.Exercise.Exercise.MultipleChoiceController;
import controller.game.backend.Utils.Exercise;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;

public abstract class ExerciseController<T extends Exercise> extends ScreenControl {

    public static final int QUESTIONNUMBER = 20;
    protected int score;

    protected int health = 3;

    protected int totalQuestions;

    protected T currentExercise;
    protected int questionIndex;
    @FXML
    protected AnchorPane finishPane;
    @FXML
    protected ImageView blood1, blood2, blood3;

    protected String userAnswer;
    @FXML
    protected Label question, scoreLabel, highestScoreLabel, questionIndexLabel, finishGameScoreLabel, finishGameHighestScoreLabel;
    protected ArrayList<T> exerciseList;

    @FXML
    protected ImageView gameIcon;
    protected Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public final ImageView correctIcon = new ImageView(getClass().getResource("/com/example/bestdictionaryever/Game/Image/correctIcon.png").toExternalForm());
    public final ImageView incorrectIcon = new ImageView(getClass().getResource("/com/example/bestdictionaryever/Game/Image/incorrectIcon.png").toExternalForm());
    public final ImageView exerciseIcon = new ImageView(getClass().getResource("/com/example/bestdictionaryever/Game/Image/gameIcon.png").toExternalForm());

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
        if (this.currentExercise.isCorrect(userAnswer)) {
            playCorrectEffect();
            gameIcon.setImage(correctIcon.getImage());
            score++;
            showHighestScore();
            scoreLabel.setText("Score: " + score);
            if (option != null) option.getStyleClass().add("correctOption");
            alertInformation("Correct", explaination).showAndWait();
        } else {
            playIncorrectEffect();
            gameIcon.setImage(incorrectIcon.getImage());
            if (option != null) option.getStyleClass().add("incorrectOption");
            showHealth(--health);
            alertInformation("Incorrect", explaination).showAndWait();
        }
        userAnswer = null;
        questionIndex++;
        setNextQuestion();
    }

    private void showHealth(int health) {
        if (health == 3) {
            blood1.setVisible(true);
            blood2.setVisible(true);
            blood3.setVisible(true);
        } else if (health == 2) {
            blood3.setVisible(false);
            System.out.println("lost one health");
        } else if (health == 1) {
            blood2.setVisible(false);
            System.out.println("lost two health");
        } else if (health == 0) {
            blood1.setVisible(false);
            System.out.println("lost three health");
        }
    }

    public void showScore_Ques() {
        scoreLabel.setText("Score: " + score);
        questionIndexLabel.setText("Question: " + (questionIndex + 1) + "/" + totalQuestions);
    }

    public abstract void showHighestScore();


    public Alert alertInformation(String title, String content) {
        if (title.equals("Choose Option")) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.getDialogPane().setPrefWidth(400);
            alert.getDialogPane().setPrefHeight(200);
        } else if (title.equals("Back to choose topic") || title.equals("Back to choose game")) {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.getDialogPane().setPrefWidth(400);
            alert.getDialogPane().setPrefHeight(200);
        } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setPrefWidth(500);
            alert.getDialogPane().setPrefHeight(200);
        }

        alert.setTitle(null);
        alert.setHeaderText(null);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setHeaderText(null);
        if (title.equals("Congratulations")) {
            content = "Congratulations! You're actually excellent!";
        }
        if (title.equals("Choose Option")) {
            content = "You have not selected any option yet!\n" + content;
        } else if (title.equals("Correct")) {
            content = "Your answer is correct! You got a new point!\n Explanation:\n" + content;
        } else if (title.equals("Incorrect")) {
            content = "Your answer is incorrect! The correct answer is: " + currentExercise.getCorrectAnswer() + "\n" + content;
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

    protected void playCongratulationsEffect() {
        playEffect(congratulationsMediaPlayer);
    }

    public void UpdateScore(String column, int newScore) {
        System.out.println("Going to update Score = " + newScore + " for column = " + column);
        user.updateScoreTopic(column, newScore);
    }

    public int getHighestScore(String column) {
        return user.getHighestScore(column);
    }

    public void backToChooseGame() {
        alertInformation("Back to choose game", "Are you sure you want to back to choose game?").showAndWait();
        if (alert.getResult().getText().equals("OK")) {
            super.chooseGame();
        }
    }

    public abstract void finishGameScreen();

    public abstract void playAgain();

    public abstract void quit();

}
