package com.example.bestdictionaryever;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
     @Override
     public void start(Stage stage) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("dashboard.fxml"));
         Scene scene = new Scene(fxmlLoader.load());
         stage.setTitle("Dictionary");
         stage.setScene(scene);
         stage.show();
     }

     public static void main(String[] args) throws IOException {
        //DictionaryCommandLine.dictionaryBasis();
//        DictionaryCommandLine.dictionaryAdvanced();
        launch();
        //return;
     }
}
