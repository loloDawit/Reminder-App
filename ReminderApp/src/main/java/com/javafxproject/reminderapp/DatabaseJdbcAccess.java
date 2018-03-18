/*
 * Class has all the methods to connect and get all, add, update and delete 
 * reminders from a database. 
 * 
 */
package com.javafxproject.reminderapp;
import java.util.List;
import  org.springframework.jdbc.core.JdbcTemplate;
/**
 *
 * @author dawitabera
 */
class DatabaseJdbcAccess {
    private JdbcTemplate jdbcTemplate; 
    private static final String GET_ALL_REMINDERS = "SELECT * FROM REMINDERS_TABLE";
    private static final String INSERT_REMINDER =
			"INSERT INTO REMINDERS_TABLE  VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_REMINDER =
			"UPDATE REMINDERS_TABLE SET name=?, notes=?, date=?, time=?, priority=?, completed=? WHERE name=? AND date=?";
    private static final String DELETE_REMINDER =
			"DELETE FROM REMINDERS_TABLE WHERE name=? AND date=?";

    public DatabaseJdbcAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    /**
     * 
     * @return a list of reminders
     */
    public List<Reminder> getAllReminders(){
        
    }
        
}
