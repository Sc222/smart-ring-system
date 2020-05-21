package ru.sc222.smartringapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Location {
    @PrimaryKey
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
}