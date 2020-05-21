package ru.sc222.smartringapp.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Action {
    @Ignore //todo refactor, move to string resource
    public static String NOT_DEFINED = "Не назначено";


    @PrimaryKey
    public long actionId;
    public String actionCategory; //Освещение, бытовая техника и т.д.
    public String actionDescription;  //включить чайник и так далее (сделать какой-то класс???)
    //todo ---long action code or something like this

    public int icon; //id of icon drawable

    public Action(String actionCategory, String actionDescription, int icon) {
        this.actionCategory = actionCategory;
        this.actionDescription = actionDescription;
        this.icon = icon;
    }
}
