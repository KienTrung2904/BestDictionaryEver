module com.example.bestdictionaryever {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
//    requires javafx.media;
    requires freetts;

    opens com.example.bestdictionaryever to javafx.fxml;
    exports com.example.bestdictionaryever;
    exports controller.translation;
    exports controller.login;
    opens controller.login ;
    exports controller.dashboard;
    opens controller.dashboard;
    exports controller.topic;
    opens controller.topic;
    exports controller.library;
    opens controller.library;

}

