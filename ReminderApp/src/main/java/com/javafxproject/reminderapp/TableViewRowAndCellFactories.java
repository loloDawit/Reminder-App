/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafxproject.reminderapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.Tooltip;

/**
 *
 * @author dawitabera
 */
public class TableViewRowAndCellFactories {

    /**
     *
     * @return table cell with custom date formating 
     */
    public TableCell<Reminder, LocalDate> getTableCellWithDateFormatting() {
        TableCell<Reminder, LocalDate> cell = new TableCell<Reminder,LocalDate>(){
           @Override
           protected void updateItem(LocalDate date, boolean empty){
               super.updateItem(date, empty);
               if(date == null || empty){
                   setText(null);
               }else{
                   setText(date.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy")));
               }
           }
        };
        cell.setAlignment(Pos.CENTER);
        return cell;
    }
    /**
     * 
     * @return Table cell with custom time formating 
     */
    public TableCell<Reminder, LocalTime> getTableCellWithFormating(){
        TableCell<Reminder, LocalTime> cell = new TableCell<Reminder,LocalTime>(){
            @Override
            protected void updateItem(LocalTime time, boolean empty){
                super.updateItem(time, empty);
                if(time == null || empty){
                    setText(null);
                }else{
                    setText(time.format(DateTimeFormatter.ofPattern("HH:mm")));
                }
            }
        };
        cell.setAlignment(Pos.CENTER);
        return cell;
    }
    /**
     * 
     * @return Table row with tooltip, reminder notes text upto 100 chars
     */
    public TableRow<Reminder> getTooltipTableRow(){
        TableRow<Reminder> row = new TableRow<Reminder>(){
            @Override
            protected void updateItem(Reminder reminder, boolean empty){
                super.updateItem(reminder, empty);
                if(empty){
                    setTooltip(null);
                }else{
                    String notes = reminder.getNotes();
                    if((notes == null) || (notes.isEmpty())){
                        setTooltip(null);
                    }else{
                        notes = (notes.length() > 100) ?
                                notes.substring(0, 100) + "...":
                                notes;
                        setTooltip(new Tooltip(notes));
                    }
                }
            }
        };
        return row;
    }
    
}
