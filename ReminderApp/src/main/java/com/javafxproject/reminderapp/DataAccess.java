/*
 * Class is a data accessor for the application. 
 * Interacts with the database access program DatabaseJdbcAccess. Maintains the
 * reminders data in a collection. 
 * Has method to CURDE reminder operations used by the application. 
 */
package com.javafxproject.reminderapp;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Priority;

/**
 *
 * @author dawitabera
 */
class DataAccess {
    private DatabaseJdbcAccess databaseAccess;
    private ObservableList<Reminder> reminderData; 

    

    public DataAccess(DatabaseJdbcAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }
    /**
     * Method retrieves all the reminders from the database when the application 
     * starts. 
     * The reminders are stored locally in the collection through this method
     */
    public void loadRemindersFromDatabase(){
        reminderData = FXCollections.observableList(databaseAccess.getAllReminders());
    }
    /**
     * 
     * @return data from the observable list 
     */
    public ObservableList<Reminder> getAllReminders(){
        return reminderData;
    }
    /**
     * 
     * @param reminder newly created reminder is saved/added both in the database and 
     *                 in the collection 
     */
    public void addReminder(Reminder reminder){
        reminderData.add(reminder);
        databaseAccess.addReminder(reminder);
    }
    /**
     * 
     * @param index .   location of the current reminder to be updated 
     * @param newReminder 
     */
    public void updateReminder(int index, Reminder newReminder){
        Reminder oldReminder = new Reminder(); 
        reminderData.set(index, newReminder); 
        databaseAccess.updateReminder(oldReminder, newReminder);
    }
    /**
     * 
     * @param tobeDeleted reminder to be deleted 
     */
    public void deleteReminder(Reminder tobeDeleted){
        reminderData.remove(tobeDeleted);
        databaseAccess.deleteReminder(tobeDeleted);
    }
    /**
     * 
     * @param group
     * @return 
     */
    public ObservableList<Reminder> getTableListData(ReminderGroup group){
        Predicate<Reminder> reminderPredicate = null; 
        switch(group){
            case PRIORITY:
                reminderPredicate = ReminderPredicates.PRIORITY;
                break;
            case COMPLETED:
                reminderPredicate = ReminderPredicates.COMPLETED;
                break;
            case TODAY:
                reminderPredicate = ReminderPredicates.TODAYS;
            case OVERDUE:
                reminderPredicate = ReminderPredicates.OVER_DUE;
            default:
                reminderPredicate = ReminderPredicates.ALL;     
        }
        List<Reminder> resuList = reminderData.stream()
                                              .filter(reminderPredicate)
                                              .collect(Collectors.toList());
        return FXCollections.observableList(resuList);
    }
    
}
