package controller.game.Exercise.Exercise;

import com.example.bestdictionaryever.DatabaseConnection;
import controller.FXMLControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MultipleChoiceApp extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.bestdictionaryever.Application.class.getResource("Game/FXML/TopicWord.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
        FXMLControl.setPrimaryStage(stage);
        DatabaseConnection.initialize();
    }

    public static void main(String[] args) throws IOException {
        launch();
        //return;
    }
}

