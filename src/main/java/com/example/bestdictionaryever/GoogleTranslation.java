package com.example.bestdictionaryever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleTranslation {

    private static final String scriptURL = "https://script.google.com/macros/s/AKfycbyVpfwhmbUSeWveS4uNynNJjvwZTAh356HljddZArlE8MKRlqdMUSWR-0EpP65Kno1rbQ/exec";

    private static final String translateURL = "https://translate.google.com/translate_tts?ie=UTF-8&q=";
    public static String getTranslation(String inputText, String inputLang, String outputLang) {
        try {
            String urlStr = scriptURL +
                    "?q=" + URLEncoder.encode(inputText, "UTF-8") +
                    "&target=" + outputLang +
                    "&source=" + inputLang;

            URL obj = URI.create(urlStr).toURL();
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to perform translation";
        }
    }

//    public static String getAudioLink(String text, String languageCode) {
//        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
//        String url = translateURL
//                + encodedText
//                + "&tl="
//                + languageCode
//                + "&client=tw-ob";
//
//        return url;
//    }

    public static void main(String[] args) {
        System.out.println(GoogleTranslation.getTranslation("This is a book", "en", "vi"));
    }

}
