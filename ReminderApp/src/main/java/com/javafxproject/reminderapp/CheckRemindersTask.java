/*
 * Class extends TimerTask and overrides its run() method.
 * The class is the input task for the Timer which schedules the
 * tasks and is initiated from the app. The tasks:
 * (i) one task at the start of the app and (ii) subsequent tasks
 * at one minute interval thru the duration of the app.
 * The first task notifies all the overdue reminders. The later
 * tasks notify the due reminder at that minute.
 * The reminder notification is shown in an Alert.
 */
package com.javafxproject.reminderapp;

import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;

/**
 *
 * @author dawitabera
 */
public class CheckRemindersTask extends TimerTask{
    private DataAccess dataAccess;
    private Predicate<Reminder> predicate = ReminderPredicates.OVER_DUE;
    
    private List<Point2D.Double> alertPositions;
    private int nextAlertPosIndex;
    
    public CheckRemindersTask(DataAccess dataAccess){
        this.dataAccess = dataAccess;
        createAlertPositions();
        nextAlertPosIndex = 0;
    }

    @Override
    public void run() {
       List<Reminder> DUEReminders = getDueReminders();
       showNotification(DUEReminders);
       predicate = ReminderPredicates.DUE_NOW;
    }

    private void createAlertPositions() {
        Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
        double x = rectangle2D.getWidth() / 5; 
        double y = rectangle2D.getHeight() /5;
        Point2D.Double position = null;
        alertPositions = new ArrayList<>();
        for(int i=0; i<10; i++){
            position = new Point2D.Double(x,y);
            alertPositions.add(position);
            x +=25;
            y +=25;
        }
    }

    private List<Reminder> getDueReminders() {
        ObservableList<Reminder> reminders = dataAccess.getAllReminders();
        return reminders.stream().filter(predicate).collect(Collectors.toList());
    }

    private void showNotification(List<Reminder> DUEReminders) {
        for(Reminder reminder : DUEReminders){
            Platform.runLater(() -> showReminderAlert(reminder));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                
            }
        }
    }

    private void showReminderAlert(Reminder reminder) {
        Alert alert = new Alert(AlertType.NONE); // a modal alert
        // Format alert content
	String name = reminder.getName();		
	alert.setTitle("Reminder - " + name);
	alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
	String content = name + " " +
			(reminder.getPriority() ? "[Priority]" : "") + "\n" +
			reminder.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) +
			"  " +
			reminder.getTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
	alert.setContentText(content);
		
	// Get alert's position
	Point2D.Double p = getNextAlertPosition();		
	alert.setX(p.getX());
	alert.setY(p.getY());
		
	// Display alert
	alert.show();
	Toolkit.getDefaultToolkit().beep();
        
    }

    private Point2D.Double getNextAlertPosition() {
        Point2D.Double position = alertPositions.get(nextAlertPosIndex);
	nextAlertPosIndex =
			(nextAlertPosIndex == 9) ? nextAlertPosIndex = 0 : ++nextAlertPosIndex;
	return position;
    }
    
    
}
