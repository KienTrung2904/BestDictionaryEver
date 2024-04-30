package com.example.bestdictionaryever;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech implements ComponentManager {
    private Voice voice;


    public TextToSpeech() {
        initialize();
    }

    @Override
    public void initialize() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        String voiceName = "kevin16";
        this.voice = VoiceManager.getInstance().getVoice(voiceName);
        if (voice == null) {
            System.err.println("Cannot find a voice named kevin16. Please specify a different voice.");
            System.exit(1);
        }
    }

    public void speak(String text) {
        voice.allocate();
        voice.speak(text);
        voice.deallocate();
    }

    public static void main(String[] args) {
        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.speak("pineapple");
    }
}
