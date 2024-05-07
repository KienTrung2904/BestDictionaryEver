package controller.game.backend.Exercises.Dictation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import controller.TopicWord.backend.Utils.Description;

public class DictationDescription extends Description {
    @JsonProperty("type")
    private String type = "Dictation";

    @JsonProperty("sentence")
    private String sentence;

    @JsonProperty("sentenceWithBlank")
    private String sentenceWithBlank;

    @JsonProperty("wordBlank")
    private String wordBlank;

    @JsonProperty("translation")
    private String translation;


    public DictationDescription() {
        super();
    }

    public DictationDescription(String sentence, String sentenceWithBlank, String wordBlank, String translation) {
        super();
        this.sentence = sentence;
        this.sentenceWithBlank = sentenceWithBlank;
        this.wordBlank = wordBlank;
        this.translation = translation;
    }

    public Dictation getDictation(DictationDescription dictationDescription) {
        return new Dictation(sentence, sentenceWithBlank, wordBlank, translation);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setSentenceWithBlank(String sentenceWithBlank) {
        this.sentenceWithBlank = sentenceWithBlank;
    }

    public String getWordBlank() {
        return wordBlank;
    }

    public void setWordBlank(String wordBlank) {
        this.wordBlank = wordBlank;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return "type: " + type + "\n"
                + "sentence: " + sentence + "\n"
                + "sentenceWithBlank: " + sentenceWithBlank + "\n"
                + "wordBlank: " + wordBlank + "\n"
                + "translation: " + translation;
    }
}
