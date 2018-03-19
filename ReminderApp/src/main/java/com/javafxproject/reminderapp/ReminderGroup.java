/*
 * Enum defines the reminder groups. 
 * This enum also stores the enum constants 
 * formatted strings as a List<String> and a Map<String, ReminderGroup> for
 * populating a ListView and lookup respectively. The formatted string
 * for a constant is for example, "Completed" and COMPLETED respectively.
 */
package com.javafxproject.reminderapp;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author dawitabera
 */
public enum ReminderGroup {
    REMINDERS, 
    TODAY,
    OVERDUE,
    COMPLETED,
    Priority;
    /**
     * key   --> formated string 
     * value --> enum constant 
     */
    private static final Map<String, ReminderGroup> MAP = new HashMap<>();
    private static final List<String> LIST = new ArrayList<>();
    
    static {
        for(ReminderGroup group: EnumSet.allOf(ReminderGroup.class)){
            String string = getEnumString(group);
            MAP.put(string, group);
            LIST.add(string);
    }
    }
    /**
     * 
     * @param group
     * @return 
     */
    private static String getEnumString(ReminderGroup group) {
        String string = group.toString().toLowerCase();
        
        return (string.substring(0,1).toUpperCase()+string.substring(1,string.length()));
    }
    /**
     * 
     * @param string
     * @return 
     */
    public static ReminderGroup getGroup(String string){
        return MAP.get(string);
    }
    /**
     * 
     * @return 
     */
    public static ObservableList<String> getAsFromatStrings(){
        return FXCollections.observableList(LIST);
    }
}
