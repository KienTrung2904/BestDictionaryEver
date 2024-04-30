package controller.TopicWord.backend.TopicWords.DetailedTopicWord;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.TopicWord.backend.TopicWords.SimpleTopicWord.SimpleTopicWord;
import controller.TopicWord.backend.Utils.Description;
import controller.game.backend.Exercises.Dictation.DictationDescription;
import controller.game.backend.Exercises.MultipleChoice.MultipleChoiceDescription;
import controller.game.backend.Utils.Exercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static controller.TopicWord.backend.TopicWords.SimpleTopicWord.SimpleTopicWordLoad.getSimpleTopicWordList;
import static controller.TopicWord.backend.Utils.TopicScanner.scanForTopics;

public class DetailTopicWordLoad {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TOPIC_WORD_DIR = "src/main/java/controller/TopicWord/backend/Topics/";
    private static final String SIMPLE_TOPIC_WORD_DIR = "src/main/java/controller/TopicWord/backend/Words/SimpleTopicWords.txt";

    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<SimpleTopicWord> simpleTopicWordList = new ArrayList<>();
    private static HashMap<SimpleTopicWord, DetailedTopicWord> simpleTopicWordToDetailedTopicWordMap = new HashMap<>();

    public static final HashMap<String, ArrayList<DetailedTopicWord>>   globalFullDetailedTopicWordMap = getDetailedTopicWordMap();
    public static final ArrayList<SimpleTopicWord> globalFullSimpleTopicWordList = getSimpleTopicWordList();

    private static void addMissingFields(DetailedTopicWord detailedTopicWord, String topic) {
        String phonetic = detailedTopicWord.getDefinition().getPhonetic();
        if (phonetic.charAt(0) != '/') {
            phonetic = '/' + phonetic + '/';
            detailedTopicWord.getDefinition().setPhonetic(phonetic);
        }

        detailedTopicWord.getDefinition().setTopic(topic);
       // detailedTopicWord.getDefinition().setAudioLink(googleTranslate.getAudioLink(detailedTopicWord.getDefinition().getWord()));

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
//            MultipleChoiceDescription multipleChoiceDescription = (MultipleChoiceDescription) description;
//            Exercise exercise = multipleChoiceDescription.getMultipleChoice(multipleChoiceDescription);
//            detailedTopicWord.getQuiz().setExercise(exercise);

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
        ArrayList<String> topics = scanForTopics();
        HashMap<String, ArrayList<DetailedTopicWord>> detailedTopicWordMap = new HashMap<>();
//com.fasterxml.jackson.databind.exc.InvalidTypeIdException: Could not resolve type id 'Dictation' as a subtype of `controller.TopicWord.backend.Utils.Description`: known type ids = [MultipleChoice] (for POJO property 'description')
// at [Source: (File); line: 91, column: 19] (through reference chain: controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailedTopicWordContainer["words"]->java.util.ArrayList[3]->controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailedTopicWord["quiz"]->controller.TopicWord.backend.Utils.Quiz["description"])
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

    public static ArrayList<DetailedTopicWord> getDetailedTopicWordListFromSimpleTopicWordList(ArrayList<SimpleTopicWord> simpleTopicWordList) {
        ArrayList<DetailedTopicWord> detailedTopicWordList = new ArrayList<>();
        for (SimpleTopicWord simpleTopicWord : simpleTopicWordList) {
            detailedTopicWordList.add(simpleTopicWordToDetailedTopicWordMap.get(simpleTopicWord));
        }
        return detailedTopicWordList;
    }

    public static void main(String[] args) {
        System.out.println("Trung");
        System.out.println(globalFullSimpleTopicWordList.size());
        for (SimpleTopicWord simpleTopicWord : globalFullSimpleTopicWordList) {
            System.out.println(simpleTopicWord);
        }



    }
}
