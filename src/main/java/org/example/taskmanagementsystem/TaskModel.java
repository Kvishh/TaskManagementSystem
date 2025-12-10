package org.example.taskmanagementsystem;

import javafx.beans.property.*;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class TaskModel {
    private StringProperty task;
    private StringProperty status;
    private StringProperty priority;
    private ObjectProperty<LocalDate> dueDate;
    private ObjectProperty<ImageView> trashCanImage;
    private BooleanProperty checked;

    public TaskModel(String task, String status, String priority, LocalDate dueDate, ImageView image, Boolean checked){
        this.task = new SimpleStringProperty(task);
        this.status = new SimpleStringProperty(status);
        this.priority = new SimpleStringProperty(priority);
        this.dueDate = new SimpleObjectProperty<>(dueDate); //StringProperty (without the simple) is abstract, with Simple being the concrete class that implements/inherits the StringProperty
        this.trashCanImage = new SimpleObjectProperty<>(image);
        this.checked = new SimpleBooleanProperty(checked) ;
    }

    public TaskModel(String task, String status, String priority, LocalDate dueDate){
        this.task = new SimpleStringProperty(task);
        this.status = new SimpleStringProperty(status);
        this.priority = new SimpleStringProperty(priority);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
    }

    public StringProperty getTaskProperty() {
        return task;
    }

    public String getTaskValue(){
        return this.task.getValue();
    }

    public void updateTask(String task) { this.task = new SimpleStringProperty(task); }

    public StringProperty getStatusProperty() {
        return this.status;
    }

    public String getStatusValue(){
        return this.status.getValue();
    }

    public void updateStatus(String status) { this.status = new SimpleStringProperty(status); }

    public StringProperty getPriorityProperty() {
        return this.priority;
    }

    public String getPriorityValue(){ return this.priority.getValue(); }

    public void updatePriority(String priority) { this.priority = new SimpleStringProperty(priority); }

    public ObjectProperty<LocalDate> getDueDateProperty() {
        return this.dueDate;
    }

    public LocalDate getDueDateValue() { return this.dueDate.get(); }

    public void updateDueDate(LocalDate date) { this.dueDate = new SimpleObjectProperty<>(date); }

    public ObjectProperty<ImageView> getTrashCanImageProperty() {
        return this.trashCanImage;
    }

    public ImageView getTrashCanValue() { return this.trashCanImage.getValue(); }

    public BooleanProperty getCheckedProperty() {
        return checked;
    }

    public boolean getCheckedValue(){ return checked.getValue(); }

    public void setChecked(boolean check) {this.checked.setValue(check);}

    void print(){
        System.out.println(this.task + ", " + this.status + ", " + this.priority + ", " + this.dueDate);
    }
}
