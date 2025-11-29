module org.example.taskmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.taskmanagementsystem to javafx.fxml;
    exports org.example.taskmanagementsystem;
}