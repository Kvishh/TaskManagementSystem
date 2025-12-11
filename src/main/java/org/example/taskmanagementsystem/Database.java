package org.example.taskmanagementsystem;

import java.sql.*;

public class Database {

    private final String url = "jdbc:sqlite:src/main/db/tasks.sqlite";
    private Connection connection;
    private Statement stmt;

    public Database(){
        try {
            this.connection = DriverManager.getConnection(url);

            this.stmt = connection.createStatement();

            this.stmt.execute("CREATE TABLE IF NOT EXISTS tasks(" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "Done INTEGER NOT NULL," +
                    "Task TEXT NOT NULL," +
                    "Status TEXT NOT NULL," +
                    "Priority TEXT NOT NULL," +
                    "Due_date TEXT NOT NULL)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int add(String task, String status, String priority, String date) {
        int key = -1;

        try {
            String insertQuery = "INSERT INTO tasks (Done, Task, Status, Priority, Due_date) VALUES (0, ?, ?, ?, ?)";
            PreparedStatement pt = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            pt.setString(1, task);
            pt.setString(2, status);
            pt.setString(3, priority);
            pt.setString(4, date);
            pt.executeUpdate();

            ResultSet rs = pt.getGeneratedKeys();
            if (rs.next()) key = rs.getInt(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return key;
    }

    public ResultSet retrieve(){
        ResultSet rs = null;

        try {
            rs = this.stmt.executeQuery("SELECT * FROM tasks");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return rs;
    }

    public void deleteTask(int id){
        try{
            String deleteQuery = "DELETE FROM tasks WHERE Id = ?";

            PreparedStatement pt = this.connection.prepareStatement(deleteQuery);
            pt.setString(1, String.valueOf(id));
            pt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateTask(String task, String status, String priority, String date, int id){
        try {
            String updateQuery = "UPDATE tasks SET Task = ?, Status = ?, Priority = ?, Due_date = ? WHERE id = ?";

            PreparedStatement pt = this.connection.prepareStatement(updateQuery);
            pt.setString(1, task);
            pt.setString(2, status);
            pt.setString(3, priority);
            pt.setString(4, date);
            pt.setString(5, String.valueOf(id));
            pt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateDone(boolean isDone, int id){
        try {
            String updateQuery = "UPDATE tasks SET Done = ? WHERE id = ?";
            PreparedStatement pt = this.connection.prepareStatement(updateQuery);

            if (isDone){
                pt.setInt(1, 1);
            } else {
                pt.setInt(1, 0);
            }
            pt.setInt(2, id);
            pt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
