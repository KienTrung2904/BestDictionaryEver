package controller.TopicWord.backend.TopicWords.DetailedTopicWord;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.TopicWord.backend.TopicWords.SimpleTopicWord.SimpleTopicWord;
import controller.TopicWord.backend.TopicWords.SimpleTopicWord.SimpleTopicWordLoad;
import controller.TopicWord.backend.Utils.Description;
import controller.game.backend.Exercises.Dictation.DictationDescription;
import controller.game.backend.Exercises.MultipleChoice.MultipleChoiceDescription;
import controller.game.backend.Utils.Exercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class DetailTopicWordLoad {
    public static class DetailedTopicWordContainer {
        private String topic;
        private ArrayList<DetailedTopicWord> words;

        public DetailedTopicWordContainer() {

        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public ArrayList<DetailedTopicWord> getWords() {
            return words;
        }

        public void setWords(ArrayList<DetailedTopicWord> words) {
            this.words = words;
        }
    }
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TOPIC_WORD_DIR = "src/main/java/controller/TopicWord/backend/TopicBank/";
    private static final String SIMPLE_TOPIC_WORD_DIR = "src/main/java/controller/TopicWord/backend/TopicBank/SimpleTopicWords.txt";

    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<SimpleTopicWord> simpleTopicWordList = new ArrayList<>();
    private static HashMap<SimpleTopicWord, DetailedTopicWord> simpleTopicWordToDetailedTopicWordMap = new HashMap<>();

    public static final HashMap<String, ArrayList<DetailedTopicWord>> globalFullDetailedTopicWordMap = getDetailedTopicWordMap();

    private static void addMissingFields(DetailedTopicWord detailedTopicWord, String topic) {
        String phonetic = detailedTopicWord.getDefinition().getPhonetic();
        if (phonetic.charAt(0) != '/') {
            phonetic = '/' + phonetic + '/';
            detailedTopicWord.getDefinition().setPhonetic(phonetic);
        }

        detailedTopicWord.getDefinition().setTopic(topic);
        Description description = detailedTopicWord.getQuiz().getDescription();

        if ((detailedTopicWord.getQuiz().getExerciseType()).contains("Dictation")) {
            DictationDescription dictationDescription = (DictationDescription) description;
            Exercise exercise = dictationDescription.getDictation(dictationDescription);
            detailedTopicWord.getQuiz().setExercise(exercise);
        } else {
            MultipleChoiceDescription multipleChoiceDescription = (MultipleChoiceDescription) description;
            Exercise exercise = multipleChoiceDescription.getMultipleChoice(multipleChoiceDescription);
            detailedTopicWord.getQuiz().setExercise(exercise);
        }

    }

    public static ArrayList<DetailedTopicWord> getWordsFromTopic(String topicName) {
        File file = new File(TOPIC_WORD_DIR + topicName + ".txt");
        try {
            DetailedTopicWordContainer container = objectMapper.readValue(file, DetailedTopicWordContainer.class);
            String topic = container.getTopic();

            HashSet<DetailedTopicWord> detailedTopicWordSet = new HashSet<>(container.getWords());
            ArrayList<DetailedTopicWord> detailedTopicWordList = new ArrayList<>(detailedTopicWordSet);

            for (DetailedTopicWord detailedTopicWord : detailedTopicWordList) {
                addMissingFields(detailedTopicWord, topicName);

                String word = detailedTopicWord.getDefinition().getWord();
                simpleTopicWordList.add(new SimpleTopicWord(topic, word));

                SimpleTopicWord simpleTopicWord = new SimpleTopicWord(topic, word);
                simpleTopicWordToDetailedTopicWordMap.put(simpleTopicWord, detailedTopicWord);
            }

            return detailedTopicWordList;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static HashMap<String, ArrayList<DetailedTopicWord>> getDetailedTopicWordMap() {
        String[] topicNames = {"Animal", "Body", "Business", "Character", "Fashion", "Food", "Idiom", "PhraseVerbs", "Plants", "Sports", "Tech", "Weather"};
        ArrayList<String> topics  = new ArrayList<>(Arrays.asList(topicNames));
        HashMap<String, ArrayList<DetailedTopicWord>> detailedTopicWordMap = new HashMap<>();
        simpleTopicWordList.clear();
        simpleTopicWordToDetailedTopicWordMap.clear();

        for (String topic : topics) {
            ArrayList<DetailedTopicWord> detailedTopicWordList = getWordsFromTopic(topic);
            detailedTopicWordMap.put(topic, detailedTopicWordList);
        }

        saveToSimpleTopicWordFile();

        return detailedTopicWordMap;
    }

    private static void saveToSimpleTopicWordFile() {
        sb.setLength(0);
        sb.append("{\n" + "\"words\": [\n");

        int simpleTopicWordListSize = simpleTopicWordList.size();
        for (int i = 0; i < simpleTopicWordListSize; ++i) {
            SimpleTopicWord simpleTopicWord = simpleTopicWordList.get(i);
            sb.append("{\"topic\": \"").append(simpleTopicWord.getTopic()).append("\", \"word\": \"").append(simpleTopicWord.getWord()).append("\"}");

            if (i < simpleTopicWordListSize - 1) {
                sb.append(",");
            }

            sb.append(System.lineSeparator());
        }

        sb.append("]\n" + "}");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SIMPLE_TOPIC_WORD_DIR))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
