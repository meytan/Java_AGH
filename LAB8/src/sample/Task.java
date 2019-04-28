package sample;

import javafx.scene.control.ListCell;

import java.time.LocalDate;


public class Task extends ListCell {
    private String title;
    private Priority priority;
    private LocalDate expDate;
    private String description;

    public Task(String title, Priority priority, LocalDate expDate, String description) {
        this.title = title;
        this.priority = priority;
        this.expDate = expDate;
        this.description = description;
    }

    public void edit(String title, Priority priority, LocalDate expDate, String description) {
        this.title = title;
        this.priority = priority;
        this.expDate = expDate;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    @Override
    public String toString() {
        return title;
    }
}
