package com.example.bestdictionaryever.dashboard.topic;

import com.example.bestdictionaryever.ComponentManager;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;

public class topicIntroduction implements ComponentManager {
    private ImageView topicAvatar;
    private Label topicName;
    private TextFlow topicDescription;
    private int index = 1;
    private int size = 0;
    private Topic topic = new Topic();
    private ArrayList<String> topicList = new ArrayList<>();

    public topicIntroduction(ImageView topicAvatar, Label topicName, TextFlow topicDescription) {
        this.topicAvatar = topicAvatar;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.initialize();
    }

    @Override
    public void initialize() {
        topicList = topic.getTopicList();
        size = topicList.size();

    }

    public void show() {
        topic.setTopic(topicList.get(this.index));
        topicName.setText(topic.getTopicName());
        topicDescription.getChildren().clear();
        topicDescription.getChildren().add(new Text(topic.getTopicDescription()));
        topicAvatar.setImage(new Image(topic.getTopicAvatar()));
    }

    public void toLeft() {
        index --;
        index = (index + size) % 10;
        show();
    }

    public void toRight() {
        index ++;
        index = index  % 10;
        show();
    }
}
