package org.example.taskmanagementsystem;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import org.controlsfx.control.spreadsheet.Grid;

import java.time.LocalDate;

public class AddDialog extends Dialog<Result> {

    private GridPane dialogPane;
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
        this.dialogPane = pane;
        addLabelsToPane();
        addInputsToPane();
        modifyInputs();
        setWindow();

        this.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.CANCEL){
                addButton.setStyle("-fx-background-color: rgb(98, 94, 192); -fx-text-fill: white; -fx-background-radius: 40px");
            } else if(buttonType == ButtonType.OK){
                addButton.setStyle("-fx-background-color: rgb(98, 94, 192); -fx-text-fill: white; -fx-background-radius: 40px");
                return new Result(this.getTaskInput(), this.getStatusOption(), this.getPriorityOption(), this.getDueDate());
            }
            return null;
        });
    }

    private void addLabelsToPane(){
        for (int i = 0; i<this.labels.length; i++){
            labels[i].setFont(Font.font(16));
            dialogPane.add(labels[i], 0, i);
        }
    }

    private void addInputsToPane(){
        for (int i = 0; i<this.inputs.length; i++){
            dialogPane.add(inputs[i], 1, i);
        }
    }

    private void modifyInputs(){
        this.taskInput.setPromptText("Task");
        this.statusOptions.getItems().addAll("To Do", "Done");
        this.statusOptions.getSelectionModel().selectFirst();priorityOptions.getItems().addAll("Urgent", "Important", "Minor");
        this.priorityOptions.getSelectionModel().selectFirst();
        this.priorityOptions.getSelectionModel().selectFirst();
    }

    private void setWindow(){
        this.dialogPane.setPadding(new Insets(30));
        this.setHeaderText("Add a new task");
        this.setTitle("TrackTask (Task Management System)");
        this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        this.getDialogPane().setContent(this.dialogPane);
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

}
