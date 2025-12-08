package org.example.taskmanagementsystem;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
        TableView<TaskModel> table = new TableView<>();
        table.setEditable(true);
        String url = "/org/example/taskmanagementsystem/styles/images/trashCanRegularFull.png";


        buildTable(table, url);

        addButton.getStyleClass().add("addButton");
        addButton.setCursor(Cursor.HAND);
        addButton.setMinSize(100, 40);

        // Space between the add button and text tracktask
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.setStyle("-fx-background-color: rgb(45, 45, 55)");
        header.setPrefHeight(100);
        header.setAlignment(Pos.CENTER);
        //scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        mainHeader.setFont(Font.font("Tahoma", 34));
        mainHeader.setFill(Color.WHITE);

        addButton.setOnMousePressed((e) -> {
            AddDialog addDialog = new AddDialog(new GridPane(10, 10), addButton);

            Optional<TaskModel> result = addDialog.showAndWait();
            result.ifPresent(res -> {
                res.print();
                table.getItems().add(new TaskModel(res.getTaskValue(),
                        res.getStatusValue(),
                        res.getPriorityValue(),
                        res.getDueDateValue(),
                        new ImageView(new Image(getClass().getResourceAsStream(url), 24, 24, true, false)),
                        false));
                //STRUCTURE:                       Task       Status  Priority     Due Date      trash can image
                //table.getItems().add(new Result("Breathe", "To do", "Urgent", LocalDate.now(), trashCanImage));
            });
        });

        header.setPadding(new Insets(20));
        header.getChildren().addAll(addButton, spacer, mainHeader);
        header.setSpacing(50);
        root.setTop(header);
        root.setCenter(table);

//        mainHeader.setStyle("-fx-fill: blue");
        scene.getStylesheets().add(this.getClass().getResource("/org/example/taskmanagementsystem/styles/stylesheet.css").toExternalForm());

        //CSS
        // /org/example/taskmanagementsystem/styles/stylesheet.css
        //CSS

        root.setStyle("-fx-background-color: rgb(32, 33, 45)");
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setResizable(false);
        stage.setTitle("TrackTask (Task Management System)");
        stage.setScene(scene);
        stage.show();
    }

    public void buildTable(TableView table, String url){
        TableColumn<TaskModel, Boolean> done = new TableColumn<>("Done");
        done.setCellValueFactory(d -> d.getValue().checkedProperty());
        done.setCellFactory(CheckBoxTableCell.forTableColumn(done));
        done.setResizable(false);
        done.setMinWidth(103.3);

        TableColumn<TaskModel, String> task = new TableColumn<>("Task");
        task.setCellValueFactory(t -> t.getValue().getTaskProperty());
        task.setCellFactory(x -> {
            TableCell<TaskModel, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (item == null) {
                        setText("");
                    } else {
                        setText(item);
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt-> {
                System.out.println("Clicked task");
                TaskModel o = (TaskModel) table.getSelectionModel().getSelectedItem();
                System.out.println(o.getTaskValue()); // this prints the task written in the clicked cell
            });

            return cell;
        });
        task.setResizable(false);
        task.setMinWidth(220.7);

        TableColumn<TaskModel, String> status = new TableColumn<>("Status");
        status.setCellValueFactory(s -> s.getValue().getStatusProperty());
        status.setCellFactory(x -> {
            TableCell<TaskModel, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (item == null) {
                        setText("");
                    } else {
                        setText(item);
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt-> System.out.println("Clicked status"));

            return cell;
        });
        status.setResizable(false);
        status.setMinWidth(123.3);

        TableColumn<TaskModel, String> priority = new TableColumn<>("Priority");
        priority.setCellValueFactory(p -> p.getValue().getPriorityProperty());
        priority.setCellFactory(x -> {
            TableCell<TaskModel, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (item == null) {
                        setText("");
                    } else {
                        setText(item);
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt-> System.out.println("Clicked priority"));

            return cell;
        });
        priority.setResizable(false);
        priority.setMinWidth(123.3);

        TableColumn<TaskModel, LocalDate> dueDate = new TableColumn("Due date");
        dueDate.setCellValueFactory(d -> d.getValue().getDueDateProperty());
        dueDate.setCellFactory(x -> {
            TableCell<TaskModel, LocalDate> cell = new TableCell<>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty){
                    super.updateItem(item, empty);

                    if (item == null){
                        setText("");
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("EE, MMMM dd")));
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt-> System.out.println("Clicked due date"));

            return cell;
        });
        dueDate.setResizable(false);
        dueDate.setMinWidth(123.3);

        TableColumn<TaskModel, ImageView> delete = new TableColumn("Delete");
        delete.setCellValueFactory( d -> d.getValue().getTrashCanImageProperty());
        delete.setCellFactory(x -> {
            TableCell<TaskModel, ImageView> cell = new TableCell<>() {
                @Override
                protected void updateItem(ImageView item, boolean empty){
                    super.updateItem(item, empty);

                    if (item == null){
                        setText("");
                    } else {
                        setGraphic(item);
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt-> System.out.println("Clicked delete"));

            return cell;
        });
        delete.setResizable(false);
        delete.setMinWidth(103.3);

        table.getColumns().addAll(done, task, status, priority, dueDate, delete);
        table.getItems().add(new TaskModel("Finish final programming final project", "To do", "Urgent", LocalDate.now(), new ImageView(new Image(getClass().getResourceAsStream(url), 24, 24, true, false)), false));
    }
}
