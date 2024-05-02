package com.example.bestdictionaryever.dictionary.API_Dictionary;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javazoom.jl.player.Player;

public abstract class API {
    private static String url;

    public static void setUrl(String _url) {
        url = _url;
    }

    protected static String getData() {
        int reponse = 0;
        try {
            URL url_API = new URL(url);
            HttpURLConnection connect = (HttpURLConnection) url_API.openConnection();
            connect.setRequestMethod("GET");
            reponse = connect.getResponseCode();
            StringBuilder respon = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                respon.append(inputLine);
            }
            in.close();
            return respon.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return("HTTP failed : " + String.valueOf(reponse));
        }
    }

    protected static Player getAudio() {
        try {
            URL url_API = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url_API.openConnection();
            InputStream audio = connection.getInputStream();
            new Player(audio).play();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
