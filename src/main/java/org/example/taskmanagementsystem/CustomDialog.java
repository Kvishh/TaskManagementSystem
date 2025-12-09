package org.example.taskmanagementsystem;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class CustomDialog extends Dialog<TaskModel> {

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

    private String textPrimaryButton;

    public CustomDialog(GridPane pane){
        //super(); Implicit call to parent class
        this.gridPane = pane;
        this.gridPane.setPadding(new Insets(30));
        this.setTitle("TrackTask (Task Management System)");
        addLabelsToPane();
        addInputsToPane();
        modifyInputs();

        // Styling of dialog
        this.getDialogPane().getStyleClass().add("myDialog");
        this.taskInput.getStyleClass().add("textInput");
        this.statusOptions.getStyleClass().add("options");
        this.priorityOptions.getStyleClass().add("options");
        this.getDialogPane().getStylesheets().add(getClass().getResource("/org/example/taskmanagementsystem/styles/dialog.css").toExternalForm());

        Platform.runLater(() -> taskInput.requestFocus());

        //In Main, addDialog.showAndWait() returns Optional of type Result due to this
        this.setResultConverter(buttonType -> {
             if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
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

    public void setMyOwnHeaderText(String header){ //be called in Main
        this.setHeaderText(header);
    }

    public void setHeaderTextPrimaryButton(String header){ //be called in Main
        this.textPrimaryButton = header;
    }

    private ButtonType createPrimaryButton(){
        return new ButtonType(this.textPrimaryButton, ButtonBar.ButtonData.OK_DONE);
    }

    private ButtonType createCancelButton(){
        return new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    }

    private void addButtonsToDialog(){
        ButtonType primaryButton = createPrimaryButton();
        ButtonType cancelButton = createCancelButton();

        this.getDialogPane().getButtonTypes().addAll(primaryButton, cancelButton);
        this.getDialogPane().lookupButton(primaryButton).setId("primaryButton"); // prev value addButton
        this.getDialogPane().lookupButton(cancelButton).setId("cancelButton"); //
    }

    public void buildDialog(){
        addButtonsToDialog();
        this.getDialogPane().setContent(this.gridPane);
    }

    public void getAndSetTaskRow(String task){
        this.taskInput.setText(task);
    }

    public void getAndSetStatusRow(String status){
        this.statusOptions.setValue(status);
    }

    public void getAndSetPriorityRow(String priority){
        this.priorityOptions.setValue(priority);
    }

    public void getAndSetDateRow(LocalDate date){
        this.dueDatePicker.setValue(date);
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
