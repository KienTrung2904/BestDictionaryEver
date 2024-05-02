package com.example.bestdictionaryever;

import com.example.bestdictionaryever.dictionary.API_Dictionary.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleTranslation extends API {

    private static final String scriptURL = "https://script.google.com/macros/s/AKfycbyVpfwhmbUSeWveS4uNynNJjvwZTAh356HljddZArlE8MKRlqdMUSWR-0EpP65Kno1rbQ/exec";

    private static final String translateURL = "https://translate.google.com/translate_tts?ie=UTF-8&q=";

    public static String getTranslation(String inputText, String inputLang, String outputLang) {
        try {
            String urlStr = scriptURL +
                    "?q=" + URLEncoder.encode(inputText, "UTF-8") +
                    "&target=" + outputLang +
                    "&source=" + inputLang;

            API.setUrl(urlStr);
            return API.getData();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to perform translation";
        }
    }
}




