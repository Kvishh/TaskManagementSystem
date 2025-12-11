package org.example.taskmanagementsystem;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TableCreator {

    private TableView<TaskModel> table;
    private Database db;
    private CustomDialog customDialog;
    private TableColumn<TaskModel, Number> idColumn;
    private TableColumn<TaskModel, Boolean> doneColumn;
    private TableColumn<TaskModel, String> taskColumn;
    private TableColumn<TaskModel, String> statusColumn;
    private TableColumn<TaskModel, String> priorityColumn;
    private TableColumn<TaskModel, LocalDate> dueDateColumn;
    private TableColumn<TaskModel, ImageView> deleteColumn;

    public TableCreator(Database db, CustomDialog customDialog){
        this.table = new TableView<>();
        this.db = db;
        this.customDialog = customDialog;
        this.idColumn = new TableColumn<>("Id");
        this.doneColumn = new TableColumn<>("Done");
        this.taskColumn = new TableColumn<>("Task");
        this.statusColumn = new TableColumn<>("Status");
        this.priorityColumn = new TableColumn<>("Priority");
        this.dueDateColumn = new TableColumn<>("Due date");
        this.deleteColumn = new TableColumn<>("Delete");

        this.table.setEditable(true);

        buildIdColumn();
        buildDoneColumn();
        buildTaskColumn();
        buildStatusColumn();
        buildPriorityColumn();
        buildDueDateColumn();
        buildDeleteColumn();

        this.table.getColumns().addAll(idColumn, doneColumn, taskColumn, statusColumn, priorityColumn, dueDateColumn, deleteColumn);
        table.getColumns().forEach(c -> {
            c.setSortable(false);
            c.setReorderable(false);
            c.setResizable(false);
        });
    }

    private void buildIdColumn(){
        this.idColumn.setCellValueFactory(i -> i.getValue().getIdProperty());
        this.idColumn.setCellFactory(x -> {
            return new TableCell<TaskModel, Number>(){
                @Override
                protected void updateItem(Number number, boolean b) {
                    super.updateItem(number, b);

                    if (number == null){
                        setText(null);
                    } else {
                        setText(String.valueOf(number));
                    }
                }
            };
        });
        this.idColumn.setVisible(false);
    }

    private void buildDoneColumn(){
        this.doneColumn.setCellValueFactory(d -> d.getValue().getCheckedProperty());
        this.doneColumn.setCellFactory(x -> {

            CheckBox checkBox = new CheckBox();
            TableCell<TaskModel, Boolean> cell = new TableCell<>() {

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null) {
                        setGraphic(null);
                    } else {
                        checkBox.setSelected(item);
                        setGraphic(checkBox);
                    }
                }
            };

            checkBox.selectedProperty().addListener(((observableValue, aBoolean, t1) -> {
                cell.getTableRow().getItem().setChecked(t1);
                boolean isChecked =  cell.getTableRow().getItem().getCheckedValue();

                if (isChecked) {
                    cell.getTableRow().getStyleClass().add("checked");
                    this.db.updateDone(isChecked, cell.getTableRow().getItem().getIdValue());
                } else {
                    cell.getTableRow().getStyleClass().remove("checked");
                    this.db.updateDone(isChecked, cell.getTableRow().getItem().getIdValue());
                }
            }));

            return cell;
        });
        this.doneColumn.setMinWidth(103.3);
        this.doneColumn.getStyleClass().add("col-justify");
    }

    private void buildTaskColumn(){
        this.taskColumn.setCellValueFactory(t -> t.getValue().getTaskProperty());
        this.taskColumn.setCellFactory(x -> {
            TableCell<TaskModel, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {
                        setText("");
                    } else {
                        setText(item);
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
                System.out.println("Clicked task");
                TaskModel rowItem = this.table.getSelectionModel().getSelectedItem();

                if (evt.getClickCount() == 2) {
                    showEditDialog(rowItem);
                }
            });
            return cell;
        });
        this.taskColumn.setMinWidth(220.7);
        this.taskColumn.getStyleClass().add("col-left-center");
    }

    private void buildStatusColumn(){
        this.statusColumn.setCellValueFactory(s -> s.getValue().getStatusProperty());
        this.statusColumn.setCellFactory(x -> {
            TableCell<TaskModel, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {
                        setText("");
                    } else {
                        setText(item);
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
                System.out.println("Clicked status");
                TaskModel rowItem = this.table.getSelectionModel().getSelectedItem();

                if (evt.getClickCount() == 2) {
                    showEditDialog(rowItem);
                }
            });

            return cell;
        });
        this.statusColumn.setMinWidth(123.3);
        this.statusColumn.getStyleClass().add("col-justify");
    }

    private void buildPriorityColumn(){
        this.priorityColumn.setCellValueFactory(p -> p.getValue().getPriorityProperty());
        this.priorityColumn.setCellFactory(x -> {

            TableCell<TaskModel, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {
                        setText("");
                    } else if (item.equals("Urgent")) {
                        setText(item);
                        getStyleClass().remove("urgent");
                        getStyleClass().add("urgent");
                    } else if (item.equals("Important")) {
                        setText(item);
                        getStyleClass().remove("important");
                        getStyleClass().add("important");
                    } else if (item.equals("Minor")) {
                        setText(item);
                        getStyleClass().remove("minor");
                        getStyleClass().add("minor");
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
                System.out.println("Clicked priority");
                TaskModel rowItem = this.table.getSelectionModel().getSelectedItem();

                if (evt.getClickCount() == 2) {
                    showEditDialog(rowItem);
                }
            });

            return cell;
        });
        this.priorityColumn.setMinWidth(123.3);
        this.priorityColumn.getStyleClass().add("col-justify");
    }

    private void buildDueDateColumn(){
        this.dueDateColumn.setCellValueFactory(d -> d.getValue().getDueDateProperty());
        this.dueDateColumn.setCellFactory(x -> {
            TableCell<TaskModel, LocalDate> cell = new TableCell<>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null) {
                        setText("");
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("EE, MMMM dd")));
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
                System.out.println("Clicked due date");
                TaskModel rowItem = this.table.getSelectionModel().getSelectedItem();

                if (evt.getClickCount() == 2) {
                    showEditDialog(rowItem);
                }
            });

            return cell;
        });
        this.dueDateColumn.setMinWidth(123.3);
        this.dueDateColumn.getStyleClass().add("col-justify");
    }

    private void buildDeleteColumn(){
        this.deleteColumn.setCellValueFactory(d -> d.getValue().getTrashCanImageProperty());
        this.deleteColumn.setCellFactory(x -> {
            TableCell<TaskModel, ImageView> cell = new TableCell<>() {
                @Override
                protected void updateItem(ImageView item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null) {
                        setText("");
                    } else {
                        getStyleClass().remove("delete-cell");
                        getStyleClass().add("delete-cell");
                        setGraphic(item);
                    }
                }
            };

            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
                try {
                    System.out.println("Clicked delete");

                    System.out.println("Id of deleted row: " + cell.getTableRow().getItem().getIdValue());
                    this.db.deleteTask(cell.getTableRow().getItem().getIdValue());
                    this.table.getItems().remove(this.table.getSelectionModel().getSelectedIndex());
                    this.table.getSelectionModel().clearSelection();
                    this.table.refresh();
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("The row clicked is null. There is nothing to delete.");
                }
            });
            return cell;
        });
        this.deleteColumn.setMinWidth(103.3);
        this.deleteColumn.getStyleClass().add("col-justify");
    }

    private void showEditDialog(TaskModel rowItem) {
        try {
            this.customDialog.getAndSetTaskRow(rowItem.getTaskValue());
            this.customDialog.getAndSetStatusRow(rowItem.getStatusValue());
            this.customDialog.getAndSetPriorityRow(rowItem.getPriorityValue());
            this.customDialog.getAndSetDateRow(rowItem.getDueDateValue());

            Optional<TaskModel> result = this.customDialog.showAndWait();
            result.ifPresent(res -> {
                System.out.println("\nNew task value: " + res.getTaskValue());
                System.out.println("New status value: " + res.getStatusValue());
                System.out.println("New priority value: " + res.getPriorityValue());
                System.out.println("New dueDate value: " + res.getDueDateValue());
                System.out.println("New id value: " + this.table.getSelectionModel().getSelectedItem().getIdValue() + "\n");
                this.db.updateTask(res.getTaskValue(), res.getStatusValue(), res.getPriorityValue(), res.getDueDateValue().toString(), this.table.getSelectionModel().getSelectedItem().getIdValue());

                this.table.getSelectionModel().getSelectedItem().updateTask(res.getTaskValue());
                this.table.getSelectionModel().getSelectedItem().updateStatus(res.getStatusValue());
                this.table.getSelectionModel().getSelectedItem().updatePriority(res.getPriorityValue());
                this.table.getSelectionModel().getSelectedItem().updateDueDate(res.getDueDateValue());
                this.table.refresh();
            });
            this.table.getSelectionModel().clearSelection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("The row clicked is null. There is nothing to edit.");
        }
    }

    public TableView<TaskModel> getTable() {
        return this.table;
    }

}
