package com.example.bestdictionaryever;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.nio.file.Files;


public class Audio {
    public static void playAudio(String text, String lang) {
        String audioLink = GoogleTranslation.getAudioLink(text, lang);
        try {
            File file = File.createTempFile("temp", ".mp3");
            Files.write(file.toPath(), audioLink.getBytes());

            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

        } catch (Exception e) {
            System.out.println("Error: Audio.playAudio(): ");
            e.printStackTrace();
        }
    }
}
