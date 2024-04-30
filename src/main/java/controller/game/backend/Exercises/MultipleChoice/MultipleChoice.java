package controller.game.backend.Exercises.MultipleChoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.game.backend.Utils.Exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MultipleChoice extends Exercise {
    @JsonProperty("question")
    private String question;

    @JsonProperty("options")
    private Options options;

    @JsonProperty("correctAnswer")
    private String correctAnswer;

    @JsonProperty("explanation")
    private String explanation;

    private static final String path = "src/main/java/controller/game/backend/ExerciseBank/MultipleChoice/MultipleChoice-Blank.txt";
//    private static String[] type = {"Blank", "Antonym", "Synonym"};

    public MultipleChoice(String question, Options options, String correctAnswer, String explanation) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanationFormat(explanation);
    }

    private String explanationFormat(String explanation) {
        String[] parts = explanation.split("(A:|B:|C:|D:)");

        StringBuilder result = new StringBuilder();

        char option = 'A';

        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                result.append(option).append(". ").append(part.trim()).append("\n");

                option++;
            }
        }

        return result.toString();
    }

    private static MultipleChoice loadFromJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode optionsNode = jsonNode.get("options");

            String question = jsonNode.get("question").asText();
            Options options = new Options();
            String correctAnswer = jsonNode.get("correctAnswer").asText();
            String explanation = jsonNode.get("explanation").asText();

            options.setOptionA(optionsNode.get("A").asText());
            options.setOptionB(optionsNode.get("B").asText());
            options.setOptionC(optionsNode.get("C").asText());
            options.setOptionD(optionsNode.get("D").asText());

            return new MultipleChoice(question, options, correctAnswer, explanation);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new MultipleChoice();
    }
    public static ArrayList<MultipleChoice> loadFromBank() {


            ArrayList<String> jsonList = new ArrayList<>(readJsonFile(path));

            ArrayList<MultipleChoice> multipleChoiceList = new ArrayList<>();

            for (String json : jsonList) {
                multipleChoiceList.add(loadFromJson(json));
            }

            HashSet<MultipleChoice> multipleChoiceSet = new HashSet<>(multipleChoiceList);

            multipleChoiceList = new ArrayList<>(multipleChoiceSet);

            return multipleChoiceList;

    }

    public MultipleChoice() {

    }

    @Override
    protected void generateExercise(String promptName) {

    }

    @Override
    public boolean isCorrect(String userAnswer) {
        return userAnswer.equals(correctAnswer);
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getOptionA() {
        return options.getOptionA();
    }
    public String getOptionB() {
        return options.getOptionB();
    }
    public String getOptionC() {
        return options.getOptionC();
    }
    public String getOptionD() {
        return options.getOptionD();
    }

    @Override
    public String toString() {
        return "Question: " + question + "\n" +
                "Options: " + options + "\n" +
                "Correct Answer: " + correctAnswer + "\n" +
                "Explanation: " + explanation;
    }

    public static void main(String[] args) {
        List<MultipleChoice> multipleChoiceList = MultipleChoice.loadFromBank();
        System.out.println("size of multiple choice is: " + multipleChoiceList.size());
    }
}
