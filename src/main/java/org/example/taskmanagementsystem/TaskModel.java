package org.example.taskmanagementsystem;

import javafx.beans.property.*;
import javafx.scene.image.ImageView;
import java.time.LocalDate;

/*
* This is an object model for the table view. When instantiating a TableView class,
* you need to specify the type of object that will be contained withing the table.
* Since the table includes String, Integer, Boolean, and Objects, they have been
* compiled to one object type.
* The attributes of this class all implements the interface ObservableValue (e.g.
* StringProperty, IntegerProperty) since the TableColumn's cellValueFactory attribute
* needs the type to be ObservableValue. It needs to implement ObservableValue because
* ObservableValue has a method addListener, TableColumn uses this so it could reflect
* the updates done from and on the cell by user.
* */

public class TaskModel {
    private IntegerProperty id;
    private StringProperty task;
    private StringProperty status;
    private StringProperty priority;
    private ObjectProperty<LocalDate> dueDate;
    private ObjectProperty<ImageView> trashCanImage;
    private BooleanProperty checked;

    public TaskModel(String task, String status, String priority, LocalDate dueDate, ImageView image, Boolean checked, int id){
        this.id = new SimpleIntegerProperty(id);
        this.task = new SimpleStringProperty(task);
        this.status = new SimpleStringProperty(status);
        this.priority = new SimpleStringProperty(priority);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
        this.trashCanImage = new SimpleObjectProperty<>(image);
        this.checked = new SimpleBooleanProperty(checked);
    }

    public TaskModel(String task, String status, String priority, LocalDate dueDate){
        this.task = new SimpleStringProperty(task);
        this.status = new SimpleStringProperty(status);
        this.priority = new SimpleStringProperty(priority);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
    }

    public IntegerProperty getIdProperty() { return id; }

    public int getIdValue() { return id.getValue(); }

    public StringProperty getTaskProperty() {
        return task;
    }

    public String getTaskValue(){
        return this.task.getValue();
    }

    public void updateTask(String task) {this.task.setValue(task); }

    public StringProperty getStatusProperty() {
        return this.status;
    }

    public String getStatusValue(){
        return this.status.getValue();
    }

    public void updateStatus(String status) { this.status.setValue(status);}

    public StringProperty getPriorityProperty() {
        return this.priority;
    }

    public String getPriorityValue(){ return this.priority.getValue(); }

    public void updatePriority(String priority) { this.priority.setValue(priority); }

    public ObjectProperty<LocalDate> getDueDateProperty() {
        return this.dueDate;
    }

    public LocalDate getDueDateValue() { return this.dueDate.get(); }

    public void updateDueDate(LocalDate date) { this.dueDate.setValue(date); }

    public ObjectProperty<ImageView> getTrashCanImageProperty() {
        return this.trashCanImage;
    }

    public ImageView getTrashCanValue() { return this.trashCanImage.getValue(); }

    public BooleanProperty getCheckedProperty() {
        return checked;
    }

    public boolean getCheckedValue(){ return checked.getValue(); }

    public void setChecked(boolean check) {this.checked.setValue(check);}
}
