package controller.TopicWord.backend.Utils;

public class Definition {

    private String topic;
    private String word;

    private String type;

    private String phonetic;

    private String example;

    private String explain;

    private String translatedExample;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getTranslatedExample() {
        return translatedExample;
    }

    public void setTranslatedExample(String translatedExample) {
        this.translatedExample = translatedExample;
    }
}
