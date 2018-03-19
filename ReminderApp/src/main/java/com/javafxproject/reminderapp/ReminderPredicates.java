/*
 * Predicate class
 * Has definition of the predicates used for this application. 
 */
package com.javafxproject.reminderapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

/**
 *
 * @author dawitabera
 */
public class ReminderPredicates {
    public static final Predicate<Reminder> ALL = r ->true;
    public static final Predicate<Reminder> COMPLETED = r ->
                                                       (r.getCompleted()==true);
    public static final Predicate<Reminder> PRIORITY = r -> 
                                                       (r.getPriority() == true); 
    public static final Predicate<Reminder> TODAYS = r ->
                                         (r.getDate().isEqual(LocalDate.now()));
    public static final Predicate<Reminder> PAST_DAYS = r ->
                                         (r.getDate().isBefore(LocalDate.now()));
    public static final Predicate<Reminder> OVER_DUE_TODAYS_TIME = r ->
                                          r.gettTime().isBefore(LocalTime.now());
    public static final Predicate<Reminder> OVER_DUE = COMPLETED.negate().
                                                 and(PAST_DAYS.or(TODAYS
                                                  .and (OVER_DUE_TODAYS_TIME)));
    public static final Predicate<Reminder> TIME_NOW = r -> r.gettTime().equals(
                                            LocalTime.now().truncatedTo(ChronoUnit
                                            .MINUTES));
    public static final Predicate<Reminder> DUE_NOW = TODAYS.and(TIME_NOW).and(
                                            COMPLETED.negate());
    
}
