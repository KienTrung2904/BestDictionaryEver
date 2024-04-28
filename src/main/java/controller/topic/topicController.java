package controller.topic;

import com.example.bestdictionaryever.dashboard.topic.Topic;
import com.example.bestdictionaryever.dashboard.topic.topicIntroduction;
import controller.ScreenControl;
import controller.dashboard.DashboardControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class topicController extends ScreenControl {
    @FXML
    private Button vocabulary;
    @FXML
    private ImageView imageVocabulary;
    @FXML
    private Text page;
    private int index = 0;
    private int sizeTopic;
    private Topic topic = new Topic();

    public void decor() {
        this.setTopic();
        this.show();
    }

    public void setTopic() {
        topic.setTopic(topic.getTopicList().get(DashboardControl.getIndexTopic()));;
        sizeTopic = topic.getWordList().size();
    }

    public void back() {
        super.dashboard();
    }

    public void moreDetail() {
        super.library();
    }

    public void toLeft() {
        index --;
        index = (index + sizeTopic) % sizeTopic;
        this.show();
    }


    public void toRight() {
        index ++;
        index = index % sizeTopic;
        this.show();
    }

    private void show() {
        if (!topic.getTopicList().isEmpty()) {
            String indexVocabulary = String.valueOf(index + 1) + "/" + String.valueOf(sizeTopic);
            page.setText(indexVocabulary);
            vocabulary.setText(topic.getWord(index));
            vocabulary.getStyleClass().add("text-box");
            imageVocabulary.setImage(new Image(topic.getWordImage(index)));
        }
    }
}
