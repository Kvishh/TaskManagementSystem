package org.example.taskmanagementsystem;

import java.time.LocalDate;

public class Result {
    private String task;
    private String status;
    private String priority;
    private LocalDate dueDate;

    public Result(String task,String status, String priority, LocalDate dueDate){
        this.task = task;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    void print(){
        System.out.println(this.task + ", " + this.status + ", " + this.priority + ", " + this.dueDate);
    }
}
