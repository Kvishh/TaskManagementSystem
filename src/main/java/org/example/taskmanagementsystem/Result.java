package org.example.taskmanagementsystem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class Result {
    private String task;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private ImageView trashCanImage;
            //= new ImageView(new Image("file:trashCanRegularFull.png", 32, 32, false, false));

    public Result(String task, String status, String priority, LocalDate dueDate, ImageView image){
        this.task = task;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.trashCanImage = image;
    }

    public Result(String task, String status, String priority, LocalDate dueDate){
        this.task = task;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getTask() {
        return task;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public ImageView getTrashCanImage() {
        return trashCanImage;
    }

    void print(){
        System.out.println(this.task + ", " + this.status + ", " + this.priority + ", " + this.dueDate);
    }
}
