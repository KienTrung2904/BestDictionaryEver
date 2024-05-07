package controller.TopicWord.backend.Utils;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import controller.game.backend.Exercises.Dictation.DictationDescription;
import controller.game.backend.Exercises.MultipleChoice.MultipleChoiceDescription;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceDescription.class, name = "MultipleChoice"),
        @JsonSubTypes.Type(value = DictationDescription.class, name = "Dictation")
})
public abstract class Description {
    public Description() {

    }
}
