/*
 * Class Represents a reminder object 
 * 
 */
package com.javafxproject.reminderapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
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

    public String getName() {
        return name.get();
    }
    
    public SimpleStringProperty namProperty(){
        return name;
    }

    public void setName(String n) {
        name.set(n);
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String n) {
        notes.set(n);
    }
    public LocalDate getDate(){
        return date.get();
    }
    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate d) {
        date.set(d);
    }
    public LocalTime gettTime(){
        return time.get();
    }
    public SimpleObjectProperty<LocalTime> timeProperty() {
        return time;
    }

    public void setTime(LocalTime t) {
        // time is stored in min
        time.set(t.truncatedTo(ChronoUnit.MINUTES));
    }

    public boolean getPriority() {
        return priority.get();
    }

    public void setPriority(boolean p) {
        priority.set(p);
    }

    public boolean getCompleted() {
        return completed.get();
    }

    public void setCompleted(boolean complete) {
        completed.set(complete);
    }

    @Override
    public String toString() {
        return name.toString(); 
        
    }
    /**
     * Reminders are equal when their names are same on the same date.
     * @param obj to be compared to
     * @return boolean depending on the logic 
     * NOTE: 
     *      equality operator (==) compares the references (addresses in memory) 
     *      of the two Strings as two different numbers - this is known as 
     *      reference equality. In this case both String objects COULD have 
     *      the exact same value, but they are in different physical memory 
     *      locations so their references (addresses in memory) are different 
     *      and the result is false.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Reminder){
            Reminder reminder = (Reminder)obj;
            if ((reminder.getName().equals(name.get())) && (reminder.getDate().
                    isEqual(date.get()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.notes);
        hash = 59 * hash + Objects.hashCode(this.date);
        hash = 59 * hash + Objects.hashCode(this.time);
        hash = 59 * hash + Objects.hashCode(this.priority);
        hash = 59 * hash + Objects.hashCode(this.completed);
        return hash;
    }
    
    
}
