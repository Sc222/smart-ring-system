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
    public String actionName; //включить чайник и так далее (сделать какой-то класс???)

    public Action(String actionName) {
        this.actionName = actionName;
    }
}
