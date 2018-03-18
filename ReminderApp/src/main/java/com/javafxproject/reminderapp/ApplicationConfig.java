/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafxproject.reminderapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author dawitabera
 */
@Configuration
@Import(DatabaseJdbcConfig.class)
public class ApplicationConfig {
    @Bean
    public ApplicationGui applicationGui(DataAccess dataAccess, CheckRemindersTask checkRemindersTask, ReminderDialog reminderDialog ){
        return ApplicationGui(dataAccess, checkRemindersTask, reminderDialog);
    }

    private ApplicationGui ApplicationGui(DataAccess dataAccess, CheckRemindersTask checkRemindersTask, ReminderDialog reminderDialog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
