package org.example.taskmanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserInterface ui = new UserInterface();
        ui.initialize();
        Scene scene = ui.getScene();

        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setResizable(false);
        stage.setTitle("TrackTask (Task Management System)");
        stage.setScene(scene);
        stage.show();
    }

}
