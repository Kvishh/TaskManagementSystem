package org.example.taskmanagementsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        BorderPane root = new BorderPane();
//        HBox header = new HBox();
//        Scene scene = new Scene(root, 800, 600);//w=800, h=600
//        Text mainHeader = new Text("TrackTask");
//        Button addButton = new Button("+ Add new task");
//        //
//        //
//        Database db = new Database();
//
//        CustomDialog cd = new CustomDialog(new GridPane(10, 10));
//        cd.setMyOwnHeaderText("Edit task");
//        cd.setHeaderTextPrimaryButton("Done");
//        cd.buildDialog();
//
//        //TableView<TaskModel> table = new TableView<>(); // DONE
//        TableCreator tb = new TableCreator(db, cd);
//        TableView<TaskModel> table = tb.getTable();
//        table.setEditable(true); // DONE
//
//        //
//        //
//        String url = "/org/example/taskmanagementsystem/styles/images/trashCanRegularFull.png";
//
//        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
//            Node source = evt.getPickResult().getIntersectedNode();
//            while (source!=null && !(source instanceof TableRow<?>)) source = source.getParent();
//
//            if (source == null || source instanceof TableRow<?> && ((TableRow<?>) source).isEmpty()) table.getSelectionModel().clearSelection();
//        });
//
//        retrieveAndPopulate(table, db, url);
//
//        //buildTable(table, url, db);
//
//        addButton.getStyleClass().add("addButton");
//        addButton.setCursor(Cursor.HAND);
//        addButton.setMinSize(100, 40);
//
//        // Space between the add button and text tracktask
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//
//        header.getStyleClass().add("header");
//        //header.setStyle("-fx-background-color: rgb(45, 45, 55)");
//        header.setPrefHeight(100);
//        header.setAlignment(Pos.CENTER);
//
//        mainHeader.setFont(Font.font("Tahoma", 34));
//        mainHeader.setFill(Color.WHITE);
//
//        addButton.setOnMousePressed((e) -> {
//            CustomDialog customDialog = new CustomDialog(new GridPane(10, 10));
//            customDialog.setMyOwnHeaderText("Add a new task");
//            customDialog.setHeaderTextPrimaryButton("Add");
//            customDialog.buildDialog();
//
//            Optional<TaskModel> result = customDialog.showAndWait();
//            result.ifPresent(res -> {
//                if (!res.getTaskValue().isBlank()) { //isBlank (JAVA 11)
//                    int key = db.add(res.getTaskValue(), res.getStatusValue(), res.getPriorityValue(), res.getDueDateValue().toString());
//
//                    table.getItems().add(new TaskModel(res.getTaskValue(),
//                                    res.getStatusValue(),
//                                    res.getPriorityValue(),
//                                    res.getDueDateValue(),
//                                    new ImageView(new Image(getClass().getResourceAsStream(url), 24, 24, true, false)),
//                                    false,
//                                    key)
//
//                            //STRUCTURE:                       Task       Status  Priority     Due Date      trash can image
//                            //table.getItems().add(new Result("Breathe", "To do", "Urgent", LocalDate.now(), trashCanImage));
//                    );
//                    table.refresh();
//                }
//            });
//        });
//
//        header.setPadding(new Insets(20));
//        header.getChildren().addAll(addButton, spacer, mainHeader);
//        header.setSpacing(50);
//        root.setTop(header);
//        root.setCenter(table);
//
////        mainHeader.setStyle("-fx-fill: blue");
//        scene.getStylesheets().add(this.getClass().getResource("/org/example/taskmanagementsystem/styles/mainStyle.css").toExternalForm());
//
//        //CSS
//        // /org/example/taskmanagementsystem/styles/mainStyle.css
//        //CSS
//
//        //root.setStyle("-fx-background-color: rgb(32, 33, 45)");
//        root.getStyleClass().add("root");
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


//    public void buildTable(TableView<TaskModel> table, String url, Database db) {
//        CustomDialog customDialog = new CustomDialog(new GridPane(10, 10));
//        customDialog.setMyOwnHeaderText("Edit task");
//        customDialog.setHeaderTextPrimaryButton("Done");
//        customDialog.buildDialog();
//
//        TableColumn<TaskModel, Number> id = new TableColumn<>("Id");
//        id.setCellValueFactory(i -> i.getValue().getIdProperty());
//        id.setCellFactory(x -> {
//            return new TableCell<TaskModel, Number>(){
//                @Override
//                protected void updateItem(Number number, boolean b) {
//                    super.updateItem(number, b);
//
//                    if (number == null){
//                        setText(null);
//                    } else {
//                        setText(String.valueOf(number));
//                    }
//                }
//            };
//        });
//        id.setVisible(false);
//
//        TableColumn<TaskModel, Boolean> done = new TableColumn<>("Done");
//        done.setCellValueFactory(d -> d.getValue().getCheckedProperty());
//        done.setCellFactory(x -> {
//
//            CheckBox checkBox = new CheckBox();
//            TableCell<TaskModel, Boolean> cell = new TableCell<>() {
//
//                @Override
//                protected void updateItem(Boolean item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (item == null) {
//                        setGraphic(null);
//                    } else {
//                        checkBox.setSelected(item);
//                        setGraphic(checkBox);
//                    }
//                }
//            };
//
//            checkBox.selectedProperty().addListener(((observableValue, aBoolean, t1) -> {
//                cell.getTableRow().getItem().setChecked(t1);
//                boolean isChecked =  cell.getTableRow().getItem().getCheckedValue();
//
//                if (isChecked) {
//                    cell.getTableRow().getStyleClass().add("checked");
//                    db.updateDone(isChecked, cell.getTableRow().getItem().getIdValue());
//                } else {
//                    cell.getTableRow().getStyleClass().remove("checked");
//                    db.updateDone(isChecked, cell.getTableRow().getItem().getIdValue());
//                }
//            }));
//
//            return cell;
//        });
//        done.setMinWidth(103.3);
//        done.getStyleClass().add("col-justify");
//
//        TableColumn<TaskModel, String> task = new TableColumn<>("Task");
//        task.setCellValueFactory(t -> t.getValue().getTaskProperty());
//        task.setCellFactory(x -> {
//            TableCell<TaskModel, String> cell = new TableCell<>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (item == null) {
//                        setText("");
//                    } else {
//                        setText(item);
//                    }
//                }
//            };
//
//            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
//                System.out.println("Clicked task");
//                System.out.println(cell.getTableRow().getItem().getIdValue());
//                TaskModel rowItem = (TaskModel) table.getSelectionModel().getSelectedItem();
//                //System.out.println(rowItem.getTaskValue()); // this prints the task written in the clicked cell
//                //System.out.println(rowItem.getDueDateValue());
//                if (evt.getClickCount() == 2) {
//                    showEditDialog(customDialog, rowItem, table, db);
//                }
//            });
//
//            return cell;
//        });
//        task.setMinWidth(220.7);
//        task.getStyleClass().add("col-left-center");
//
//        TableColumn<TaskModel, String> status = new TableColumn<>("Status");
//        status.setCellValueFactory(s -> s.getValue().getStatusProperty());
//        status.setCellFactory(x -> {
//            TableCell<TaskModel, String> cell = new TableCell<>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (item == null) {
//                        setText("");
//                    } else {
//                        setText(item);
//                    }
//                }
//            };
//
//            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
//                System.out.println("Clicked status");
//                TaskModel rowItem = (TaskModel) table.getSelectionModel().getSelectedItem();
//
//                if (evt.getClickCount() == 2) {
//                    showEditDialog(customDialog, rowItem, table, db);
//                }
//            });
//
//            return cell;
//        });
//        status.setMinWidth(123.3);
//        status.getStyleClass().add("col-justify");
//
//        TableColumn<TaskModel, String> priority = new TableColumn<>("Priority");
//        priority.setCellValueFactory(p -> p.getValue().getPriorityProperty());
//        priority.setCellFactory(x -> {
//
//            TableCell<TaskModel, String> cell = new TableCell<>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (item == null) {
//                        setText("");
//                    } else if (item.equals("Urgent")) {
//                        setText(item);
//                        getStyleClass().remove("urgent");
//                        getStyleClass().add("urgent");
//                    } else if (item.equals("Important")) {
//                        setText(item);
//                        getStyleClass().remove("important");
//                        getStyleClass().add("important");
//                    } else if (item.equals("Minor")) {
//                        setText(item);
//                        getStyleClass().remove("minor");
//                        getStyleClass().add("minor");
//                    }
//                }
//            };
//
//            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
//                System.out.println("Clicked priority");
//                TaskModel rowItem = (TaskModel) table.getSelectionModel().getSelectedItem();
//
//                if (evt.getClickCount() == 2) {
//                    showEditDialog(customDialog, rowItem, table, db);
//                }
//            });
//
//            return cell;
//        });
//        priority.setMinWidth(123.3);
//        priority.getStyleClass().add("col-justify");
//
//        TableColumn<TaskModel, LocalDate> dueDate = new TableColumn<>("Due date");
//        dueDate.setCellValueFactory(d -> d.getValue().getDueDateProperty());
//        dueDate.setCellFactory(x -> {
//            TableCell<TaskModel, LocalDate> cell = new TableCell<>() {
//                @Override
//                protected void updateItem(LocalDate item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (item == null) {
//                        setText("");
//                    } else {
//                        setText(item.format(DateTimeFormatter.ofPattern("EE, MMMM dd")));
//                    }
//                }
//            };
//
//            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
//                System.out.println("Clicked due date");
//                TaskModel rowItem = (TaskModel) table.getSelectionModel().getSelectedItem();
//
//                if (evt.getClickCount() == 2) {
//                    showEditDialog(customDialog, rowItem, table, db);
//                }
//            });
//
//            return cell;
//        });
//        dueDate.setMinWidth(123.3);
//        dueDate.getStyleClass().add("col-justify");
//
//        TableColumn<TaskModel, ImageView> delete = new TableColumn<>("Delete");
//        delete.setCellValueFactory(d -> d.getValue().getTrashCanImageProperty());
//        delete.setCellFactory(x -> {
//            TableCell<TaskModel, ImageView> cell = new TableCell<>() {
//                @Override
//                protected void updateItem(ImageView item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (item == null) {
//                        setText("");
//                    } else {
//                        getStyleClass().remove("delete-cell");
//                        getStyleClass().add("delete-cell");
//                        setGraphic(item);
//                    }
//                }
//            };
//
//            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
//                System.out.println("Clicked delete");
//                TaskModel rowItem = (TaskModel) table.getSelectionModel().getSelectedItem();
//
//                System.out.println(cell.getTableRow().getItem().getIdValue());
//                db.deleteTask(cell.getTableRow().getItem().getIdValue());
//                table.getItems().remove(table.getSelectionModel().getSelectedIndex());
//                table.getSelectionModel().clearSelection();
//                table.refresh();
//            });
//            return cell;
//        });
//        delete.setMinWidth(103.3);
//        delete.getStyleClass().add("col-justify");
//
//        table.getColumns().addAll(id, done, task, status, priority, dueDate, delete);
//        table.getColumns().forEach(c -> {
//            c.setSortable(false);
//            c.setReorderable(false);
//            c.setResizable(false);
//        });
////        table.getItems().add(new TaskModel("Finish final programming final project",
////                "To Do",
////                "Urgent",
////                LocalDate.now(),
////                new ImageView(new Image(getClass().getResourceAsStream(url), 24, 24, true, false)),
////                false));
//    }
//
//    private void showEditDialog(CustomDialog customDialog, TaskModel rowItem, TableView<TaskModel> table, Database db) {
//        try {
//            customDialog.getAndSetTaskRow(rowItem.getTaskValue());
//            customDialog.getAndSetStatusRow(rowItem.getStatusValue());
//            customDialog.getAndSetPriorityRow(rowItem.getPriorityValue());
//            customDialog.getAndSetDateRow(rowItem.getDueDateValue());
//
//            Optional<TaskModel> result = customDialog.showAndWait();
//            result.ifPresent(res -> {
//                System.out.println("\nNew task value: " + res.getTaskValue());
//                System.out.println("New task value: " + res.getStatusValue());
//                System.out.println("New task value: " + res.getPriorityValue());
//                System.out.println("New task value: " + res.getDueDateValue());
//                System.out.println("New task value: " + table.getSelectionModel().getSelectedItem().getIdValue());
//                db.updateTask(res.getTaskValue(), res.getStatusValue(), res.getPriorityValue(), res.getDueDateValue().toString(), table.getSelectionModel().getSelectedItem().getIdValue());
//
//                table.getSelectionModel().getSelectedItem().updateTask(res.getTaskValue());
//                table.getSelectionModel().getSelectedItem().updateStatus(res.getStatusValue());
//                table.getSelectionModel().getSelectedItem().updatePriority(res.getPriorityValue());
//                table.getSelectionModel().getSelectedItem().updateDueDate(res.getDueDateValue());
//                table.refresh();
//            });
//            table.getSelectionModel().clearSelection();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println("The row clicked is null. There is nothing to edit.");
//        }
//    }
//
//    private void retrieveAndPopulate(TableView<TaskModel> table, Database db, String url){
//        try {
//            ResultSet rs = db.retrieve();
//            while (rs.next()) {
//                table.getItems().add(new TaskModel(rs.getString("Task"),
//                        rs.getString("Status"),
//                        rs.getString("Priority"),
//                        LocalDate.parse(rs.getString("Due_date")) ,
//                        new ImageView(new Image(getClass().getResourceAsStream(url), 24, 24, true, false)),
//                        rs.getInt("Done") == 1 ? true : false,
//                        rs.getInt("Id"))
//                );
//                table.refresh();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
