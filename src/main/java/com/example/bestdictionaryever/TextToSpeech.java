package com.example.bestdictionaryever;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech implements ComponentManager {

//    public static void main(String[] args) {
//        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
//        // Initialize voice manager
//
//        // Choose a voice from the available voices
//        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
//
//        Voice[] voicelist = VoiceManager.getInstance().getVoices();
//
//        for (int i = 0; i < voicelist.length; i++) {
//            System.out.println("Voice: " + voicelist[i].getName());
//        }
//
//        if (voice == null) {
//            System.err.println("Cannot find a voice named kevin16. Please specify a different voice.");
//            System.exit(1);
//        }
//
//        // Allocate the chosen voice
//        voice.allocate();
//
//        // Text to be synthesized
//        String text = "Hello, welcome to FreeTTS!";
//
//        // Synthesize speech
//        voice.speak(text);
//
//        // Deallocate the voice
//        voice.deallocate();
//    }

    private Voice voice;
    private String voiceName;


    public TextToSpeech() {
        initialize();
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }
    public String getVoiceName() {
        return voiceName;
    }

    @Override
    public void initialize() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voiceName = "kevin16";
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
