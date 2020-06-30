package ru.sc222.smartringapp.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.dto.Geoposition;


@Entity
public class Location {

    //todo background class or dictionary
    @Ignore
    public static final int[] backgrounds = {
            R.drawable.location_blue_bg,
            R.drawable.location_green_bg,
            R.drawable.location_purple_bg,
            R.drawable.location_orange_bg,
            R.drawable.location_home_outside_bg,
            R.drawable.location_home_inside_bg,
            R.drawable.location_office_outside_bg,
            R.drawable.location_office_inside_bg,
            R.drawable.location_garden_outside_bg,
            R.drawable.location_garden_inside_bg,
            R.drawable.location_outside_bg,
            R.drawable.location_outside_city_bg,
    };

    @Ignore
    public static final int[] backgroundIcons = {
            R.drawable.location_blue_bg_ic,
            R.drawable.location_green_bg_ic,
            R.drawable.location_purple_bg_ic,
            R.drawable.location_orange_bg_ic,
            R.drawable.location_home_outside_bg_ic,
            R.drawable.location_home_inside_bg_ic,
            R.drawable.location_office_outside_bg_ic,
            R.drawable.location_office_inside_bg_ic,
            R.drawable.location_garden_outside_bg_ic,
            R.drawable.location_garden_inside_bg_ic,
            R.drawable.location_outside_bg_ic,
            R.drawable.location_outside_city_bg_ic,
    };
    @PrimaryKey(autoGenerate = true)
    public long locationId;
    public int radius;
    public String locationName;
    public String locationAddress; //string address
    public double locationLongitude;
    public double locationLatitude;
    public int locationBackground; //id of background drawable
    //todo create many-to-many relation and replace with list
    public String singleClickAction;
    public String doubleClickAction;
    public String tripleClickAction;
    public String longClickAction;
    @Ignore
    private String[] commands;

    public Location(long locationId, int radius, String locationName, String locationAddress, double locationLongitude, double locationLatitude, int locationBackground, String singleClickAction, String doubleClickAction, String tripleClickAction, String longClickAction) {
        this.locationId = locationId;
        this.radius = radius;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.locationBackground = locationBackground;
        this.singleClickAction = singleClickAction;
        this.doubleClickAction = doubleClickAction;
        this.tripleClickAction = tripleClickAction;
        this.longClickAction = longClickAction;
        this.commands = new String[]{singleClickAction, doubleClickAction, tripleClickAction, longClickAction};
    }

    public Location(String locationName, Geoposition geoposition, int locationBackground, String singleClickAction, String doubleClickAction, String tripleClickAction, String longClickAction) {
        this.locationName = locationName;
        this.radius = geoposition.getRadius();
        this.locationAddress = geoposition.getAddress();
        this.locationLongitude = geoposition.getLongitude();
        this.locationLatitude = geoposition.getLatitude();
        this.locationBackground = locationBackground;
        this.singleClickAction = singleClickAction;
        this.doubleClickAction = doubleClickAction;
        this.tripleClickAction = tripleClickAction;
        this.longClickAction = longClickAction;
        this.commands = new String[]{singleClickAction, doubleClickAction, tripleClickAction, longClickAction};
    }

    public int getCommandsCount() {
        int result = 0;
        if (!singleClickAction.equals(Action.NOT_DEFINED))
            result++;
        if (!doubleClickAction.equals(Action.NOT_DEFINED))
            result++;
        if (!tripleClickAction.equals(Action.NOT_DEFINED))
            result++;
        if (!longClickAction.equals(Action.NOT_DEFINED))
            result++;
        return result;
    }

    public String getCommand(int value) {
        if (value >= 0 && value < commands.length)
            return commands[value];
        return Action.NOT_DEFINED;
    }
}