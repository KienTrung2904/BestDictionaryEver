package controller.game.Exercise.Exercise;

import controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailedTopicWord;
import controller.game.Exercise.Utils.ExerciseController;
import controller.game.backend.Exercises.MultipleChoice.MultipleChoice;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailTopicWordLoad.globalFullDetailedTopicWordMap;

public class MultipleChoiceController extends ExerciseController<MultipleChoice> implements Initializable {
    private String currentTopic;
    @FXML
    private AnchorPane topicPane, exercisePane;
    @FXML
    private Button AnimalButton, BodyButton, FoodButton, SportsButton, FashionButton, WeatherButton,
            BusinessButton, PlantsButton, optionA, optionB, optionC, optionD, optionChosen;
    @FXML
    private Button[] topicButton;
    @FXML
    private Button[] options;
    @FXML
    private Label chooseYour = new Label();
    Font font = Font.loadFont(getClass().getResource("/com/example/bestdictionaryever/font/Montserrat-Medium.ttf").toExternalForm(), 10);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setChooseTopicScreen(true);
        topicButton = new Button[]{AnimalButton, BodyButton, FoodButton, SportsButton, FashionButton, WeatherButton,
                BusinessButton, PlantsButton};
        options = new Button[]{optionA, optionB, optionC, optionD};
        for (Button topic : topicButton) {
            topic.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setCurrentTopic(topic.getText());
                }
            });
        }
        String[] optionText = {"A", "B", "C", "D"};
        for (int index = 0; index < options.length; index++) {
            int temp = index;
            options[index].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    optionChosen = options[temp];
                    userAnswer = optionText[temp];
                    System.out.println("User choose: " + userAnswer);
                    resetButtonStyle();
                    //options[temp].getStyleClass().clear();
                    options[temp].getStyleClass().add("choosingButton");
                }
            });
        }

    }

    private void goToExercise() {
        setChooseTopicScreen(false);
        setNextQuestion();
    }


    public void setNextQuestion() {
        if (health == -1 || questionIndex == totalQuestions) {
            finishGameScreen();
        }
        gameIcon.setImage(exerciseIcon.getImage());
        showScore_Ques();
        if (questionIndex == 0)  {
            randomizeExerciseList();
        }
        resetButtonStyle();
        highestScoreLabel.setText("Your highest Score: " + getHighestScore(currentTopic));
        scoreLabel.setText("Score: " + score);
        questionIndexLabel.setText("Question: " + (questionIndex + 1) + "/" + totalQuestions);
        MultipleChoice multipleChoice = exerciseList.get(questionIndex);
        question.setText(multipleChoice.getQuestion());
        question.setWrapText(true);
        correctAnswer = multipleChoice.getCorrectAnswer();
        optionA.setText("A. " + multipleChoice.getOptionA());
        optionA.setWrapText(true);
        optionB.setText("B. " + multipleChoice.getOptionB());
        optionB.setWrapText(true);
        optionC.setText("C. " + multipleChoice.getOptionC());
        optionC.setWrapText(true);
        optionD.setText("D. " + multipleChoice.getOptionD());
        optionD.setWrapText(true);
        currentExercise = multipleChoice;
        System.out.println(currentExercise);
    }
    public void showHighestScore() {
        highestScoreLabel.setText("Your highest Score: " + getHighestScore(currentTopic));
    }

    public void clickCheck(MouseEvent mouseEvent) throws InterruptedException {
        if (userAnswer == null) {
            System.out.println("Please choose an answer");
        } else {
            checkAnswer(optionChosen, currentExercise.getExplanation());
        }
    }
    public void backToChooseTopic() {
        UpdateScore(currentTopic, score);
        setChooseTopicScreen(true);
    }
    private void setCurrentTopic(String topic) {
        currentTopic = topic;
        setExerciseList(getMultipleChoiceExerciseListWithTopic(currentTopic));
        randomizeExerciseList();
        totalQuestions = exerciseList.size();
        System.out.println("Total questions: " + totalQuestions);
        score = 0;
        health = 3;
        questionIndex = 0;
        goToExercise();
    }

    public void resetButtonStyle() {
        for (Button optionEx : options) {
            // clear option chosen at previous
            optionEx.getStyleClass().clear();
            optionEx.getStyleClass().add("optionButton");
        }
    }
    private void setChooseTopicScreen(Boolean isTrue) {
        finishPane.setVisible(false);
        if (isTrue) {
            topicPane.setVisible(true);
            exercisePane.setVisible(false);
            //alert.setVisible(false);
        } else {
            topicPane.setVisible(false);
            exercisePane.setVisible(true);
        }

    }
    public static ArrayList<MultipleChoice> getMultipleChoiceExerciseListWithTopic(String topicName) {
        ArrayList<MultipleChoice> multipleChoiceList = new ArrayList<>();
        ArrayList<DetailedTopicWord> detailedTopicWords = globalFullDetailedTopicWordMap.get(topicName);
        for (DetailedTopicWord detailedTopicWord : detailedTopicWords) {
            if (detailedTopicWord.getQuiz().getExerciseType().contains("MultipleChoice")) {
                MultipleChoice exercise = (MultipleChoice) detailedTopicWord.getQuiz().getExercise();
                multipleChoiceList.add(exercise);
            }
        }
        return multipleChoiceList;
    }
    @Override
    public void finishGameScreen() {
        finishPane.setVisible(true);
        exercisePane.setVisible(false);
        topicPane.setVisible(false);
        finishGameScoreLabel.setText("Score: " + score);
        finishGameHighestScoreLabel.setText("Your highest score: " + getHighestScore(currentTopic));
    }

    @Override
    public void quit() {
        backToChooseTopic();
    }
    @Override
    public void playAgain() {
        setCurrentTopic(currentTopic);
    }
}
