package controller.game.backend.Exercises.Dictation;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.game.backend.Utils.Exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@JsonTypeName("Dictation")
public class Dictation extends Exercise {


    private String sentence;
    private String sentenceWithBlank;
    private String wordBlank;

    private String translation;

    private static final String path = "src/main/java/controller/game/backend/ExerciseBank/Dictation/Dictation-Blank.txt";

    private DictationDescription description;

    public DictationDescription getDescription() {
        return description;
    }

    public void setDescription(DictationDescription description) {
        this.description = description;
    }


    public Dictation() {

    }

    void assign(Dictation dictation) {
        this.sentence = dictation.getSentence();
        this.sentenceWithBlank = dictation.getSentenceWithBlank();
        this.wordBlank = dictation.getWordBlank();
        this.translation = dictation.translation;
    }

    public Dictation(String sentence, String sentenceWithBlank, String wordBlank, String translation) {
        this.sentence = sentence;
        this.sentenceWithBlank = sentenceWithBlank;
        this.wordBlank = wordBlank;
        this.translation = translation;
    }


    private static Dictation loadFromJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            String sentence = jsonNode.get("sentence").asText();
            String sentenceWithBlank = jsonNode.get("sentenceWithBlank").asText();
            String wordBlank = jsonNode.get("wordBlank").asText();

            if (jsonNode.has("audioLink") && jsonNode.has("translation")) {
                String audioLink = jsonNode.get("audioLink").asText();
                String translation = jsonNode.get("translation").asText();

                return new Dictation(sentence, sentenceWithBlank, wordBlank, translation);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Dictation();
    }

    public static ArrayList<Dictation> loadFromBank() {

            ArrayList<String> jsonList = new ArrayList<>(readJsonFile(path));

            ArrayList<Dictation> dictationList = new ArrayList<>();

            for (String json : jsonList) {
                dictationList.add(loadFromJson(json));
            }

            HashSet<Dictation> dictationSet = new HashSet<>(dictationList);

            dictationList = new ArrayList<>(dictationSet);

            return dictationList;

    }


    @Override
    protected void generateExercise(String query) {

    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentenceWithBlank() {
        return sentenceWithBlank;
    }

    @Override
    public String getQuestion() {
        return getSentenceWithBlank();
    }

    public void setSentenceWithBlank(String sentenceWithBlank) {
        this.sentenceWithBlank = sentenceWithBlank;
    }

    public String getWordBlank() {
        return wordBlank;
    }

    @Override
    public String getCorrectAnswer() {
        return getWordBlank();
    }

    public void setWordBlank(String wordBlank) {
        this.wordBlank = wordBlank;
    }

    @Override
    public boolean isCorrect(String userAnswer) {
        return userAnswer.equalsIgnoreCase(wordBlank);
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Sentence: ").append(sentence).append("\n");
        result.append("Sentence with blank: ").append(sentenceWithBlank).append("\n");
        result.append("Word blank: ").append(wordBlank).append("\n");
        result.append("Translation: ").append(translation).append("\n");
        return result.toString();
    }

    public static void main(String[] args) {

        ArrayList<Dictation> dictationList = new ArrayList<>(loadFromBank());
        for (Dictation dictation : dictationList) {
            System.out.println(dictation);
        }
        System.out.println("size of dictation is: " + dictationList.size());
    }
}
