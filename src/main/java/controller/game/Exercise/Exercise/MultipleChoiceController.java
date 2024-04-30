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
import java.util.HashMap;
import java.util.ResourceBundle;

import static controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailTopicWordLoad.globalFullDetailedTopicWordMap;

public class MultipleChoiceController extends ExerciseController<MultipleChoice> implements Initializable {

//    public void back() {
//        super.chooseGame();
//    }
    private String currentTopic;

    public void backToChooseTopic() {
        super.chooseTopicAndPractice();
    }

    public void chooseAnimal(MouseEvent mouseEvent) {
        setCurrentTopic("Animal");
    }
    public void chooseFood(MouseEvent mouseEvent) {
        setCurrentTopic("Food");
    }
    public void chooseSports(MouseEvent mouseEvent) {
        setCurrentTopic("Sports");
    }
    public void chooseBody(MouseEvent mouseEvent) {
        setCurrentTopic("Body");
    }
    public void chooseWeather(MouseEvent mouseEvent) {
        setCurrentTopic("Weather");
    }
    public void choosePlants(MouseEvent mouseEvent) {
        setCurrentTopic("Plants");
    }
    public void chooseBusiness(MouseEvent mouseEvent) {
        setCurrentTopic("Business");
    }
    public void chooseFashion(MouseEvent mouseEvent) {
        setCurrentTopic("Fashion");
    }

    @FXML
    private VBox topicVbox, exerciseVbox;
    @FXML
    private Button AnimalButton, BodyButton, FoodButton, SportsButton, FashionButton, WeatherButton,
            BusinessButton, PlantsButton, optionA, optionB, optionC, optionD;
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

        for (Button option : options) {
            option.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    userAnswer = option.getText();
                    System.out.println("User choose: " + userAnswer);
                    resetButtonStyle();
                    option.getStyleClass().clear();
                    option.getStyleClass().add("choosingButton");
                }
            });

        }

    }
    private void setChooseTopicScreen(Boolean isTrue) {
        if (isTrue) {
            topicVbox.setVisible(true);
            exerciseVbox.setVisible(false);
        } else {
            topicVbox.setVisible(false);
            exerciseVbox.setVisible(true);
        }
    }

    private void setCurrentTopic(String topic) {
        currentTopic = topic;
        setExerciseList(getMultipleChoiceExerciseListWithTopic(currentTopic));
        goToExercise();
    }

    private void goToExercise() {
        setChooseTopicScreen(false);
        setNextQuestion();

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
    public void setNextQuestion() {
        resetButtonStyle();
        MultipleChoice multipleChoice = exerciseList.get(questionIndex);
        question.setText(multipleChoice.getQuestion());
        question.setWrapText(true);
        optionA.setText(multipleChoice.getOptionA());
        optionB.setText(multipleChoice.getOptionB());
        optionC.setText(multipleChoice.getOptionC());
        optionD.setText(multipleChoice.getOptionD());
        if (multipleChoice.getCorrectAnswer().equals("A")) {
            multipleChoice.setCorrectAnswer(multipleChoice.getOptionA());
        } else if (multipleChoice.getCorrectAnswer().equals("B")) {
            multipleChoice.setCorrectAnswer(multipleChoice.getOptionB());
        } else if (multipleChoice.getCorrectAnswer().equals("D")) {
            multipleChoice.setCorrectAnswer(multipleChoice.getOptionC());
        } else {
            multipleChoice.setCorrectAnswer(multipleChoice.getOptionD());
        }
        currentExercise = multipleChoice;

        System.out.println(currentExercise);
    }

    public void clickCheck(MouseEvent mouseEvent) {
        if (userAnswer == null) {
            System.out.println("Please choose an answer");
        } else {
            checkAnswer();
        }
    }

    public void resetButtonStyle() {
        for (Button optionEx : options) {
            optionEx.getStyleClass().clear();
            optionEx.getStyleClass().add("bigButton");
        }
    }
}
