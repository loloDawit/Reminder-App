/*
 * Class Represents a reminder object 
 * 
 */
package com.javafxproject.reminderapp;

import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dawitabera
 */
class Reminder {
    private SimpleStringProperty name;
    private SimpleStringProperty notes;
    private SimpleObjectProperty<LocalDate> date;
    private SimpleObjectProperty<LocalTime> time;
    private SimpleBooleanProperty priority;
    private SimpleBooleanProperty completed;

    public Reminder() {
        name = new SimpleStringProperty();
        notes = new SimpleStringProperty("");
        date = new SimpleObjectProperty<>();
        time = new SimpleObjectProperty<>();
        priority = new SimpleBooleanProperty();
	completed = new SimpleBooleanProperty(false);
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public SimpleStringProperty getNotes() {
        return notes;
    }

    public void setNotes(SimpleStringProperty notes) {
        this.notes = notes;
    }

    public SimpleObjectProperty<LocalDate> getDate() {
        return date;
    }

    public void setDate(SimpleObjectProperty<LocalDate> date) {
        this.date = date;
    }

    public SimpleObjectProperty<LocalTime> getTime() {
        return time;
    }

    public void setTime(SimpleObjectProperty<LocalTime> time) {
        this.time = time;
    }

    public SimpleBooleanProperty getPriority() {
        return priority;
    }

    public void setPriority(SimpleBooleanProperty priority) {
        this.priority = priority;
    }

    public SimpleBooleanProperty getCompleted() {
        return completed;
    }

    public void setCompleted(SimpleBooleanProperty completed) {
        this.completed = completed;
    }
    
    
}
