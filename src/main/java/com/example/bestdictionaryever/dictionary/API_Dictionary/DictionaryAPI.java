package com.example.bestdictionaryever.dictionary.API_Dictionary;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.bestdictionaryever.dictionary.word;

public class DictionaryAPI extends API{
    public static void fetchDefinition(word word) {
        try {
            String target = word.getWordTarget();
            String API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/" + target;

            API.setUrl(API_URL);
            String response = API.getData();
            JSONArray json = new JSONArray(response);
            JSONObject entry = json.getJSONObject(0);

            if (entry.has("word")) {
                JSONArray phonetics = entry.getJSONArray("phonetics");

                if(phonetics.length() > 0 ) {
                    JSONObject phonetic = phonetics.getJSONObject(0);

                    if (phonetic.has("text")) {
                        word.setWordPhonetic(phonetic.getString("text"));
                    }
                }

                for (int i = 0; i < json.length(); i++) {
                    entry = json.getJSONObject(i);

                    JSONArray meanings = entry.getJSONArray("meanings");

                    for (int j = 0; j < meanings.length(); j++) {
                        JSONObject meaning = meanings.getJSONObject(j);

                        if (meaning.has("partOfSpeech")) {
                            word.addWordPartOfSpeech(meaning.getString("partOfSpeech"));
                        }

                        String partOfSpeech = meaning.getString("partOfSpeech");
                        JSONArray definitions = meaning.getJSONArray("definitions");

                        for (int k = 0; k < definitions.length(); k ++) {
                            JSONObject definition = definitions.getJSONObject(k);

                            if (definition.has("definition")) {
                                String definitionExplain = "• " + definition.getString("definition");
                                word.addWordExplain(partOfSpeech, definitionExplain);
                            }

                            if (definition.has("example")) {
                                String exampleExplain = definition.getString("example");
                                word.addWordExplain(partOfSpeech, "    ◦ " + exampleExplain);
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
