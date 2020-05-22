package ru.sc222.smartringapp.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.sc222.smartringapp.R;


@Entity
public class Location {

    //todo background class or dictionary
    @Ignore
    public static final int[] backgrounds={R.color.colorPrimary,R.drawable.location_outside_bg};

    @Ignore
    public static final List<String> backgroundNames= Arrays.asList("Основной цвет", "Лес");

    @PrimaryKey(autoGenerate = true)
    public long locationId;
    public String locationName;
    public String locationAddress; //todo create special address class
    public int locationBackground; //id of background drawable

    //todo create many-to-many relation and replace with list
    public String singleClickAction;
    public String doubleClickAction;
    public String tripleClickAction;
    public String longClickAction;

    public Location(String locationName, String locationAddress, int locationBackground, String singleClickAction, String doubleClickAction, String tripleClickAction, String longClickAction) {
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.locationBackground = locationBackground;
        this.singleClickAction = singleClickAction;
        this.doubleClickAction = doubleClickAction;
        this.tripleClickAction = tripleClickAction;
        this.longClickAction = longClickAction;
    }

    public int getCommandsCount() {
        int result = 0;
        if(!singleClickAction.equals(Action.NOT_DEFINED))
            result++;
        if(!doubleClickAction.equals(Action.NOT_DEFINED))
            result++;
        if(!tripleClickAction.equals(Action.NOT_DEFINED))
            result++;
        if(!longClickAction.equals(Action.NOT_DEFINED))
            result++;
        return result;
    }
}