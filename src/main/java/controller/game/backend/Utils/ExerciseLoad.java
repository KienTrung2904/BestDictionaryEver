package controller.game.backend.Utils;

import controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailedTopicWord;
import controller.TopicWord.backend.TopicWords.SimpleTopicWord.SimpleTopicWord;
import controller.game.backend.Exercises.Dictation.Dictation;
import controller.game.backend.Exercises.MultipleChoice.MultipleChoice;

import java.util.ArrayList;

import static controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailTopicWordLoad.globalFullDetailedTopicWordMap;

public class ExerciseLoad {
    public static ArrayList<MultipleChoice> getMultipleChoiceExerciseFromSimpleTopicWord(ArrayList<SimpleTopicWord> SimpleTopicWordList) {
//        ArrayList<MultipleChoice> multipleChoiceList = new ArrayList<>();
//        ArrayList<DetailedTopicWord> detailedTopicWordList = getDetailedTopicWordListFromSimpleTopicWordList(simpleTopicWordList);
//        for (DetailedTopicWord detailedTopicWord: detailedTopicWordList) {
//            Exercise exercise = detailedTopicWord.getQuiz().getExercise();
//            if (exercise instanceof MultipleChoice) {
//                multipleChoiceList.add((MultipleChoice) exercise);
//            }
//        }
//        return multipleChoiceList;
        return null;
    }

    public static ArrayList<MultipleChoice> getMultipleChoiceExerciseListWithTopic(String topicName) {
        ArrayList<MultipleChoice> multipleChoiceList = new ArrayList<>();
        ArrayList<DetailedTopicWord> detailedTopicWords = new ArrayList<>();
        detailedTopicWords = globalFullDetailedTopicWordMap.get(topicName);
        for (DetailedTopicWord detailedTopicWord : detailedTopicWords) {
            if (detailedTopicWord.getQuiz().getExerciseType().contains("MultipleChoice")) {
                MultipleChoice exercise = (MultipleChoice) detailedTopicWord.getQuiz().getExercise();
                multipleChoiceList.add(exercise);
            }
        }
        return multipleChoiceList;
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

    public static void main(String[] args) {
        ArrayList<Dictation> dicList = getDictationExerciseList();
        for (Dictation dic : dicList) {
            System.out.println(dic);
        }
        System.out.println(dicList.size());
    }

}
