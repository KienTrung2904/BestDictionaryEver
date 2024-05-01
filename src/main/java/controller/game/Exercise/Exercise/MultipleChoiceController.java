package controller.game.Exercise.Exercise;

import controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailedTopicWord;
import controller.game.Exercise.Utils.ExerciseController;
import controller.game.backend.Exercises.MultipleChoice.MultipleChoice;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailTopicWordLoad.globalFullDetailedTopicWordMap;

public class MultipleChoiceController extends ExerciseController<MultipleChoice> implements Initializable {
    private String currentTopic;
    @FXML
    private VBox topicVbox, exerciseVbox;
    @FXML
    private Button AnimalButton, BodyButton, FoodButton, SportsButton, FashionButton, WeatherButton,
            BusinessButton, PlantsButton, optionA, optionB, optionC, optionD, optionChosen;
    @FXML
    private Button[] topicButton;
    @FXML
    private Button[] options;


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
                    options[temp].getStyleClass().clear();
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
        if (health == -1) {
            backToChooseTopic();
            return;
        }
        if (questionIndex == 0)  {
            randomizeExerciseList();
        }
        resetButtonStyle();
        scoreLabel.setText("Score: " + score);
        questionIndexLabel.setText("Question: " + (questionIndex + 1) + "/" + totalQuestions);
        MultipleChoice multipleChoice = exerciseList.get(questionIndex);
        question.setText(multipleChoice.getQuestion());
        question.setWrapText(true);
        optionA.setText(multipleChoice.getOptionA());
        optionA.setWrapText(true);
        optionB.setText(multipleChoice.getOptionB());
        optionB.setWrapText(true);
        optionC.setText(multipleChoice.getOptionC());
        optionC.setWrapText(true);
        optionD.setText(multipleChoice.getOptionD());
        optionD.setWrapText(true);
        currentExercise = multipleChoice;
        System.out.println(currentExercise);
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
            optionEx.getStyleClass().add("bigButton");
        }
    }
    private void setChooseTopicScreen(Boolean isTrue) {
        if (isTrue) {
            topicVbox.setVisible(true);
            exerciseVbox.setVisible(false);
            //alert.setVisible(false);
        } else {
            topicVbox.setVisible(false);
            exerciseVbox.setVisible(true);
            highestScoreLabel.setText("Your highest Score: " + getHighestScore(currentTopic));
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
}
