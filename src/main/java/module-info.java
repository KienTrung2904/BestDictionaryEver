module com.example.bestdictionaryever {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.bestdictionaryever to javafx.fxml;
    exports com.example.bestdictionaryever;
}