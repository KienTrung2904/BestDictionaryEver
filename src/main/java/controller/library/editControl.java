package controller.library;

import com.example.bestdictionaryever.DatabaseConnection;
import com.example.bestdictionaryever.Word.Edit;
import com.example.bestdictionaryever.Word.Prepare;
import com.example.bestdictionaryever.dictionary.word;
import controller.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class editControl extends ScreenControl {
    @FXML
    private TextField target;
    @FXML
    private TextField partOfSpeech;
    @FXML
    private TextField definition;
    @FXML
    private TextField example;
    private static word wordData = new word();
    private static String d = "" , e = "", part;
    public static word getWord(word word) {return word;}

    public static void setWord(word _word) {
        wordData = _word;
    }

    public void decor() {
        target.setText(wordData.getWordTarget());
        Map<String, ArrayList<String>> wordExplain = wordData.getWordExplain();
        part = wordExplain.entrySet().iterator().next().getKey();
        partOfSpeech.setText(part);
        ArrayList <String> s = new ArrayList<>(wordExplain.entrySet().iterator().next().getValue());
        if (!s.isEmpty()) {
            d = s.get(0);
            definition.setText(d.substring(2));
            if (s.size() > 1) {
                e = s.get(1);
                example.setText(e.substring(6));
            }
            else {
                example.setText(e);
            }
        }
        else {
            definition.setText(d);
            example.setText(e);

        }


    }
    public void remove() {
    }

    public void back() {
        super.search();
    }

    public void save() {
        Edit.saveDefinition(target.getText(), part, partOfSpeech.getText(),  d + "\n" + e, "• " + definition.getText() + "\n" +  "    ◦ " + example.getText());
        System.out.println(target.getText());
        System.out.println(part);
        System.out.println(partOfSpeech.getText());
        System.out.println("• " + d + "\n" +  "    ◦ " + e);
        System.out.println(definition.getText() + "\n" +  "    ◦ " + example.getText());

        DatabaseConnection.initialize();
        super.librarySelect(target.getText());
    }
}
