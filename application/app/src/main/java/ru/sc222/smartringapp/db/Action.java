package ru.sc222.smartringapp.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import ru.sc222.smartringapp.R;

@Entity
public class Action {
    @Ignore //todo refactor, move to string resource
    public static String NOT_DEFINED = "Не назначено";
    @Ignore //todo refactor, move to string resource, MAKE ENUM
    public static String TYPE_ALERT = "Тревожный сигнал";
    @Ignore //todo refactor, move to string resource, MAKE ENUM
    public static String TYPE_HOME_CONTROL = "Управление умным домом";

    @Ignore
    public static final int[] icons = {
            R.drawable.ic_action_danger,
            R.drawable.ic_action_tv,
            R.drawable.ic_action_pc,
            R.drawable.ic_action_light_1,
            R.drawable.ic_action_light_2,
            R.drawable.ic_action_light_3,
            R.drawable.ic_action_kettle,
    };

    @PrimaryKey(autoGenerate = true)
    public long actionId;
    public String actionCategory; //Освещение, бытовая техника и т.д.
    public String actionDescription;  //включить чайник и так далее (сделать какой-то класс???)
    public String actionType; //сделать enum
    public String phoneNumber;
    public int icon; //id of icon drawable

    public Action(String actionCategory, String actionDescription, int icon, String actionType, String phoneNumber) {
        this.actionCategory = actionCategory;
        this.actionDescription = actionDescription;
        this.icon = icon;
        this.actionType = actionType;
        this.phoneNumber = phoneNumber;
    }
}
