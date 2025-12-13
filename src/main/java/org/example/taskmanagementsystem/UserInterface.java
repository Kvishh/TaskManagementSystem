package org.example.taskmanagementsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

/*
* This class is responsible for the view and controller of the program.
* This is where every other class are instantiated and used instead of
* calling everything in Main class.
* */

public class UserInterface {
    private final Scene scene;
    private final TableCreator tableCreator;
    private final TableView<TaskModel> table;
    private final BorderPane root = new BorderPane();
    private final HBox header = new HBox();
    private final Text mainHeader = new Text("TrackTask");
    private final Button addButton = new Button("+ Add new task");
    private final Database database = new Database();
    private final CustomDialog customDialog;
    private final String imagePath = "/org/example/taskmanagementsystem/styles/images/trashCanRegularFull.png";
    private final String mainStylePath = "/org/example/taskmanagementsystem/styles/mainStyle.css";
    private final Region spacer = new Region();

    public UserInterface(){
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        this.customDialog = new CustomDialog(pane);

        this.scene = new Scene(this.root, 800, 600);
        this.tableCreator = new TableCreator(this.database, this.customDialog);
        this.table = this.tableCreator.getTable();
    }

    public void initialize(){
        scene.getStylesheets().add(this.getClass().getResource(mainStylePath).toExternalForm());

        this.table.setEditable(true);

        this.customDialog.setMyOwnHeaderText("Edit task");
        this.customDialog.setHeaderTextPrimaryButton("Done");
        this.customDialog.buildDialog();

        loseRowSelection();
        retrieveAndPopulate();

        this.addButton.getStyleClass().add("addButton");
        this.addButton.setCursor(Cursor.HAND);
        this.addButton.setMinSize(100, 40);

        HBox.setHgrow(this.spacer, Priority.ALWAYS);

        this.header.getStyleClass().add("header");
        this.header.setPrefHeight(100);
        this.header.setAlignment(Pos.CENTER);

        this.mainHeader.setFont(Font.font("Tahoma", 34));
        this.mainHeader.setFill(Color.WHITE);

        this.header.setPadding(new Insets(20));
        this.header.getChildren().addAll(this.addButton, this.spacer, this.mainHeader);
        this.header.setSpacing(50);

        this.root.setTop(header);
        this.root.setCenter(table);

        addTaskButtonHandler();
    }

    private void loseRowSelection(){
        this.scene.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
            Node source = evt.getPickResult().getIntersectedNode();
            while (source!=null && !(source instanceof TableRow<?>)) source = source.getParent();

            if (source == null || source instanceof TableRow<?> && ((TableRow<?>) source).isEmpty()) table.getSelectionModel().clearSelection();
        });
    }

    private void retrieveAndPopulate(){
        try {
            ResultSet rs = this.database.retrieve();
            while (rs.next()) {
                this.table.getItems().add(new TaskModel(rs.getString("Task"),
                        rs.getString("Status"),
                        rs.getString("Priority"),
                        LocalDate.parse(rs.getString("Due_date")) ,
                        new ImageView(new Image(getClass().getResourceAsStream(imagePath), 24, 24, true, false)),
                        rs.getInt("Done") == 1 ? true : false,
                        rs.getInt("Id"))
                );
                this.table.refresh();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addTaskButtonHandler(){
        this.addButton.setOnMousePressed((e) -> {
            GridPane pane = new GridPane();
            pane.setVgap(10);
            pane.setHgap(10);
            CustomDialog customDialog = new CustomDialog(pane);
            customDialog.setMyOwnHeaderText("Add a new task");
            customDialog.setHeaderTextPrimaryButton("Add");
            customDialog.buildDialog();

            Optional<TaskModel> result = customDialog.showAndWait();
            result.ifPresent(res -> {
                if (!res.getTaskValue().isBlank()) { //isBlank (JAVA 11)
                    int key = this.database.add(res.getTaskValue(), res.getStatusValue(), res.getPriorityValue(), res.getDueDateValue().toString());

                    this.table.getItems().add(new TaskModel(res.getTaskValue(),
                                    res.getStatusValue(),
                                    res.getPriorityValue(),
                                    res.getDueDateValue(),
                                    new ImageView(new Image(getClass().getResourceAsStream(imagePath), 24, 24, true, false)),
                                    false,
                                    key)
                    );
                    table.refresh();
                }
            });
        });
    }

    public Scene getScene() {
        return scene;
    }
}
