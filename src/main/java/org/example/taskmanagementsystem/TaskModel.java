package org.example.taskmanagementsystem;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class TaskModel {
    private StringProperty task;
    private StringProperty status;
    private StringProperty priority;
    private LocalDate dueDate;
    private ImageView trashCanImage;
            //= new ImageView(new Image("file:trashCanRegularFull.png", 32, 32, false, false));
    private BooleanProperty checked;

    public TaskModel(String task, String status, String priority, LocalDate dueDate, ImageView image, Boolean checked){
        this.task = new SimpleStringProperty(task);
        this.status = new SimpleStringProperty(status);
        this.priority = new SimpleStringProperty(priority);
        this.dueDate = dueDate;
        this.trashCanImage = image;
        this.checked = new SimpleBooleanProperty(checked) ;
    }

    public TaskModel(String task, String status, String priority, LocalDate dueDate){
        this.task = new SimpleStringProperty(task);
        this.status = new SimpleStringProperty(status);
        this.priority = new SimpleStringProperty(priority);
        this.dueDate = dueDate;
    }

    public StringProperty getTaskProperty() {
        return task;
    }

    public String getTaskValue(){
        return this.task.getValue();
    }

    public StringProperty getStatusProperty() {
        return status;
    }

    public String getStatusValue(){
        return this.status.getValue();
    }

    public StringProperty getPriorityProperty() {
        return priority;
    }

    public String getPriorityValue(){
        return this.priority.getValue();
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public ImageView getTrashCanImage() {
        return trashCanImage;
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    void print(){
        System.out.println(this.task + ", " + this.status + ", " + this.priority + ", " + this.dueDate);
    }
}
