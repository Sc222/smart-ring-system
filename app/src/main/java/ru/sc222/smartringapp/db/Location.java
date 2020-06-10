package ru.sc222.smartringapp.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.core.Geoposition;


@Entity
public class Location {

    //todo background class or dictionary
    @Ignore
    public static final int[] backgrounds={
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
    public static final int[] backgroundIcons={
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

    /*@Ignore //todo pictures
    public static final List<String> backgroundNames= Arrays.asList( //Todo string resources
            "Голубой цвет",
            "Зеленый цвет",
            "Фиолетовый цвет",
            "Оранжевый цвет",
            "Многоквартирный дом",
            "Гостиная",
            "Офисное здание",
            "Комната в офисе",
            "Дачный поселок",
            "Комната в саду",
            "Лес"
    );*/

    @PrimaryKey(autoGenerate = true)
    public long locationId;
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

    public Location(long locationId, String locationName, String locationAddress, double locationLongitude, double locationLatitude, int locationBackground, String singleClickAction, String doubleClickAction, String tripleClickAction, String longClickAction) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.locationBackground = locationBackground;
        this.singleClickAction = singleClickAction;
        this.doubleClickAction = doubleClickAction;
        this.tripleClickAction = tripleClickAction;
        this.longClickAction = longClickAction;
    }

    public Location(String locationName, Geoposition geoposition, int locationBackground, String singleClickAction, String doubleClickAction, String tripleClickAction, String longClickAction) {
        this.locationName = locationName;
        this.locationAddress = geoposition.getAddress();
        this.locationLongitude = geoposition.getLongitude();
        this.locationLatitude = geoposition.getLatitude();
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