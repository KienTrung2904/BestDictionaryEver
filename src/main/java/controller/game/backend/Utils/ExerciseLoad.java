package controller.game.backend.Utils;

import controller.TopicWord.backend.TopicWords.DetailedTopicWord.DetailedTopicWord;
import controller.TopicWord.backend.TopicWords.SimpleTopicWord.SimpleTopicWord;
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

    public static void main(String[] args) {
        ArrayList<MultipleChoice> multipleChoiceList = getMultipleChoiceExerciseListWithTopic("Animal");
        System.out.println("size of multiple choice is: " + multipleChoiceList.size());
    }

}
