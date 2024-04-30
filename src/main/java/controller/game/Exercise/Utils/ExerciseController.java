package controller.game.Exercise.Utils;

import controller.ScreenControl;
import controller.game.backend.Utils.Exercise;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ExerciseController<T extends Exercise> extends ScreenControl {
   public void back() {
         super.chooseGame();
   }
}
