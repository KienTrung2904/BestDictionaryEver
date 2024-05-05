package controller;

import controller.library.searchControl;

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

    public void topic() {
        super.loadScreenFXML("topic");
    }

    public void library() {
        super.loadScreenFXML("library");
    }

    public void search() {
        super.loadScreenFXML("search");
    }

    public void add() {
        super.loadScreenFXML("addWord");
    }

    public void librarySelect(String target) {
        searchControl.setTarget(target);
        super.loadScreenFXML("search");
    }

    public void game() {
        super.loadScreenFXML("choose-game");
    }

    public void translationPage() {
        super.loadScreenFXML("translation");
    }

}
