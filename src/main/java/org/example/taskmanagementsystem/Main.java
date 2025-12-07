package org.example.taskmanagementsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Optional;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        HBox header = new HBox();
        Scene scene = new Scene(root, 800, 600);//w=800, h=600
        Text mainHeader = new Text("TrackTask");
        Button addButton = new Button("+ Add new task");

        // Space between the add button and text tracktask
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.setStyle("-fx-background-color: rgb(45, 45, 55)");
        header.setPrefHeight(100);
        header.setAlignment(Pos.CENTER);
        //scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        mainHeader.setFont(Font.font("Tahoma", 34));
        addButton.setMinSize(100, 40);
        addButton.setCursor(Cursor.HAND);

        addButton.setStyle("-fx-background-color: rgb(98, 94, 192); -fx-text-fill: white; -fx-background-radius: 40px");
        mainHeader.setFill(Color.WHITE);

        addButton.setOnMousePressed((e) -> {
            addButton.setStyle("-fx-background-color: rgb(68, 64, 120); -fx-text-fill: white; -fx-background-radius: 40px");
            AddDialog addDialog = new AddDialog(new GridPane(10, 10), addButton);

            Optional<Result> result = addDialog.showAndWait();
            result.ifPresent(res -> res.print());
        });

        header.setPadding(new Insets(20));
        header.getChildren().addAll(addButton, spacer, mainHeader);
        header.setSpacing(50);
        root.setTop(header);

//        mainHeader.setStyle("-fx-fill: blue");
        mainHeader.setId("mainHeader");
        scene.getStylesheets().add(this.getClass().getResource("/org/example/taskmanagementsystem/styles/stylesheet.css").toExternalForm());

        //CSS
        // /org/example/taskmanagementsystem/styles/stylesheet.css
        //CSS

        root.setStyle("-fx-background-color: rgb(32, 33, 45)");
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setTitle("TrackTask (Task Management System)");
        stage.setScene(scene);
        stage.show();
    }
}
