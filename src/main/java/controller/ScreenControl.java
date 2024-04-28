package controller;

// add link page
public abstract  class ScreenControl extends  FXMLControl{
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

    public void topic() {
        super.loadScreenFXML("topic");
    }

    public void library() {
        super.loadScreenFXML("library");
    }

}
