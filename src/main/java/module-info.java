module com.example.bestdictionaryever {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
   // requires javafx.media;
    requires freetts;
    requires com.fasterxml.jackson.annotation;

    opens com.example.bestdictionaryever to javafx.fxml;
    opens controller.game to javafx.fxml;
    exports controller.game;
    exports com.example.bestdictionaryever;
    exports controller.translation;
    exports controller.login;
    opens controller.login ;
    exports controller.dashboard;
    opens controller.dashboard;

}

