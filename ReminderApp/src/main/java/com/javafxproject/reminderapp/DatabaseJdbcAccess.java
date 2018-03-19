/*
 * Class has all the methods to connect and get all, add, update and delete 
 * reminders from a database. 
 * 
 */
package com.javafxproject.reminderapp;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import  org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        return jdbcTemplate.query(GET_ALL_REMINDERS, new RowMapper<Reminder>(){
            @Override
            public Reminder mapRow(ResultSet rs, int rowNum) throws SQLException {
                // create reminder object
                Reminder reminder = new Reminder();
                reminder.setName(rs.getString("name"));
                reminder.setNotes(rs.getString("notes"));
                reminder.setDate(rs.getDate("date").toLocalDate());
                reminder.setTime(rs.getTime("time").toLocalTime());
                reminder.setPriority(rs.getBoolean("priority"));
                reminder.setCompleted(rs.getBoolean("completed"));
                return reminder;
            }
        });
    }
    /**
     * Update existing reminder
     * @param oldReminder reminder to be  updated 
     * @param newReminder newly created reminder 
     */
    public void updateReminder(Reminder oldReminder, Reminder newReminder){
        jdbcTemplate.update(UPDATE_REMINDER, 
                newReminder.getName(), 
                newReminder.getNotes(),
                Date.valueOf(newReminder.getDate()),
                Time.valueOf(newReminder.gettTime()),
                newReminder.getPriority(),
                newReminder.getCompleted(),
                oldReminder.getName(),
                Date.valueOf(oldReminder.getDate())
        );
    }
    /**
     * 
     * @param reminderToBeDeleted 
     */
    public void deleteReminder(Reminder reminderToBeDeleted){
        jdbcTemplate.update(DELETE_REMINDER, 
                reminderToBeDeleted.getName(),
                Date.valueOf(reminderToBeDeleted.getDate())
        );
    }
    /**
     * shutdown database connection 
     */
    public void killDatabase(){
        jdbcTemplate.execute("SHUTDOWN");
    }
        
}
