package controller;

// add link page
public abstract class ScreenControl extends FXMLControl{
    public static ScreenControl getScreen() {
        return screen;
    }

    public void started() {
        super.loadScreenFXML("sign-in");
    }

    public void sign_up() {
        super.loadScreenFXML("sign-up");
    }

    public void dashboard() {
        super.loadScreenFXML("dashboard");
    }

    public void translationPage() {
        super.loadScreenFXML("translation-page");
    }

    public void chooseGame() {
        super.loadScreenFXML("Game/FXML/ChooseGame");
    }

    protected void vocabulary() {
        super.loadScreenFXML("Game/FXML/MultipleChoice");
    }

    protected void listening() {
        super.loadScreenFXML("Game/FXML/Listening");
    }

    protected void chooseTopicAndPractice() {
        super.loadScreenFXML("Game/FXML/TopicWord");
    }
}
