/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafxproject.reminderapp;

import java.awt.Toolkit;
import java.util.Timer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author dawitabera
 */
public class ApplicationMain extends Application{
    private AbstractApplicationContext context;
    
    /**
     * Loads the spring application context from java based configuration. 
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ApplicationGui applicationGui = context.getBean(ApplicationGui.class);
        Parent mainView = applicationGui.getView();
        primaryStage.setScene(new Scene(mainView,925,450));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Reminder applicationn");
        primaryStage.show();
        
        // check database execption while loading the application 
        DataAccessException exception = applicationGui.getApplicationDataEx();
        if(exception != null){
            //show alert 
            String message = "There's an error while loading data from the "
                             +" database, Exiting application";
            showExceptionAlertAndExitApp(exception,message);
            
        }
    }
    /**
     * 
     * @param ex
     * @param msg 
     */
    private static void showExceptionAlertAndExitApp(Exception ex, String msg){
        
        System.out.println("*** ERROR:  " + ex.getMessage());
        Toolkit.getDefaultToolkit().beep();
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Reminder - Error");
        alert.setContentText(msg);
        alert.showAndWait();
        Platform.exit();
    }
    /**
     * 
     * @throws Exception 
     */
    @Override
    public void stop() throws Exception {
        context.getBean(DatabaseJdbcAccess.class).killDatabase();
        Timer timer = context.getBean(ApplicationGui.class).getTimer();
        if(timer != null){
            timer.cancel();
        }
        context.close();
    }
    
    public static void main(String... args) {
        Application.launch(args);
    }
    
}
