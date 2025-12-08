package org.example.taskmanagementsystem;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class AddDialog extends Dialog<TaskModel> {

    private GridPane gridPane;
    private Label taskLabel = new Label("Task:");
    private Label statusLabel = new Label("Status:");
    private Label priorityLabel = new Label("Priority:");;
    private Label dueDateLabel = new Label("Due Date");;
    private TextField taskInput = new TextField();
    private ComboBox<String> statusOptions = new ComboBox<>();
    private ComboBox<String> priorityOptions = new ComboBox<>();
    private DatePicker dueDatePicker = new DatePicker(LocalDate.now());
    private Label[] labels = {taskLabel, statusLabel, priorityLabel, dueDateLabel};;
    private Control[] inputs = {taskInput, statusOptions, priorityOptions, dueDatePicker};

    public AddDialog(GridPane pane, Button addButton){
        //super(); Implicit call to parent class
        this.gridPane = pane;
        addLabelsToPane();
        addInputsToPane();
        modifyInputs();
        setWindow();
        customizeDialog();

        //In Main, addDialog.showAndWait() returns Optional of type Result due to this
        this.setResultConverter(buttonType -> {
            if(buttonType.getText().equals("Add")){
                return new TaskModel(this.getTaskInput(), this.getStatusOption(), this.getPriorityOption(), this.getDueDate());
            }
            return null;
        });
    }

    private void addLabelsToPane(){
        for (int i = 0; i<this.labels.length; i++){
            labels[i].getStyleClass().add("label");
            labels[i].setFont(Font.font(16));
            gridPane.add(labels[i], 0, i);
        }
    }

    private void addInputsToPane(){
        for (int i = 0; i<this.inputs.length; i++){
            gridPane.add(inputs[i], 1, i);
        }
    }

    private void modifyInputs(){
        this.taskInput.setPromptText("Task");
        this.statusOptions.getItems().addAll("To Do", "Done");
        this.statusOptions.getSelectionModel().selectFirst();
        this.priorityOptions.getItems().addAll("Urgent", "Important", "Minor");
        this.priorityOptions.getSelectionModel().selectFirst();
    }

    private void setWindow(){
        this.gridPane.setPadding(new Insets(30));
        this.setHeaderText("Add a new task");
        this.setTitle("TrackTask (Task Management System)");
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);
        this.getDialogPane().lookupButton(addButton).setId("addButton");
        this.getDialogPane().lookupButton(cancelButton).setId("cancelButton");
        this.getDialogPane().setContent(this.gridPane);
    }

    public String getTaskInput(){
        return this.taskInput.getText();
    }

    public String getStatusOption(){
        return this.statusOptions.getValue();
    }

    public String getPriorityOption(){
        return this.priorityOptions.getValue();
    }

    public LocalDate getDueDate(){
        return this.dueDatePicker.getValue();
    }

    private void customizeDialog(){
        this.getDialogPane().getStyleClass().add("myDialog");
        this.taskInput.getStyleClass().add("textInput");
        Platform.runLater(() -> taskInput.requestFocus());
        this.statusOptions.getStyleClass().add("options");
        this.priorityOptions.getStyleClass().add("options");
        this.getDialogPane().getStylesheets().add(getClass().getResource("/org/example/taskmanagementsystem/styles/dialog.css").toExternalForm());
    }

}
