package controller.game.Exercise.Exercise;

import com.example.bestdictionaryever.TextToSpeech;
import controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailedTopicWord;
import controller.game.Exercise.Utils.ExerciseController;
import controller.game.backend.Exercises.Dictation.Dictation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailTopicWordLoad.globalFullDetailedTopicWordMap;

public class DictationController extends ExerciseController<Dictation> implements Initializable {

    @FXML
    private Label sentenceWithBlank;
    @FXML
    private TextField answerTextField;
    @FXML
    private Button submitButton;
    @FXML
    private ImageView speaker;
    @FXML
    private AnchorPane listeningPane;

    private TextToSpeech textToSpeech;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeningPane.setVisible(true);
        finishPane.setVisible(false);
        setExerciseList(getDictationExerciseList());
        randomizeExerciseList();
        setNextQuestion();
        submitButton.setOnAction(actionEvent -> {
            userAnswer = answerTextField.getText().toLowerCase();
            correctAnswer = currentExercise.getWordBlank().toLowerCase();
            try {
                checkAnswer(null, currentExercise.getSentence() + "\n" + currentExercise.getTranslation());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        speaker.setOnMouseClicked(mouseEvent -> {
            textToSpeech = new TextToSpeech();
            textToSpeech.speak(currentExercise.getSentence());
        });
    }

    @Override
    public void setNextQuestion() {
        if (health == -1 || questionIndex == QUESTIONNUMBER) {
            finishGameScreen();
        }
        showScore_Ques();
        answerTextField.setText("");
        if (questionIndex < QUESTIONNUMBER) {
            currentExercise = exerciseList.get(questionIndex);
            sentenceWithBlank.setText(currentExercise.getQuestion());
            questionIndexLabel.setText("Question " + (questionIndex + 1) +  "/" + QUESTIONNUMBER);
        }
    }

    public void showHighestScore() {
        highestScoreLabel.setText("Your highest Score: " + getHighestScore("Dictation"));
    }
    public static ArrayList<Dictation> getDictationExerciseList() {
        ArrayList<Dictation> dicList = new ArrayList<>();

        ArrayList<DetailedTopicWord> detailedTopicWords = new ArrayList<>();
        for (String topicName : globalFullDetailedTopicWordMap.keySet()) {
            detailedTopicWords = globalFullDetailedTopicWordMap.get(topicName);
            for (DetailedTopicWord detailedTopicWord : detailedTopicWords) {
                if (detailedTopicWord.getQuiz().getExerciseType().contains("Dictation")) {
                    Dictation exercise = (Dictation) detailedTopicWord.getQuiz().getExercise();
                    dicList.add(exercise);
                }
            }
        }
        return dicList;
    }
    @Override
    public void backToChooseGame() {
        UpdateScore("Dictation", score);
        super.backToChooseGame();
    }
    @Override
    public void finishGameScreen() {
        finishPane.setVisible(true);
        listeningPane.setVisible(false);
        finishGameScoreLabel.setText("Score: " + score);
        finishGameHighestScoreLabel.setText("Your highest score: " + getHighestScore("Dictation"));
    }
    @Override
    public void playAgain() {
        super.listening();
    }
    @Override
    public void quit() {
        super.backToChooseGame();
    }

}
