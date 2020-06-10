package ru.sc222.smartringapp.ui.locations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ru.sc222.smartringapp.db.Location;

public class LocationsViewModel extends ViewModel {

    private MutableLiveData<List<Location>> locations;
    private MutableLiveData<Integer> currentLocation;

    public LocationsViewModel() {

        locations= new MutableLiveData<>();
        locations.setValue(new ArrayList<Location>());
        currentLocation= new MutableLiveData<>();
        currentLocation.setValue(-1);
    }


    public void setLocations(List<Location> locations) {
        this.locations.setValue(locations);
    }

    public LiveData<List<Location>> getLocations() {
        return locations;
    }

    public LiveData<Integer> getCurrentLocation() {
        return currentLocation;
    }

    //todo THIS METHODS CHANGES LOCATIONS LIST AND REDRAWS ALL ACTIVITY LIST, FIX LATER
    public void setCurrentLocation(int currLocIndex) {
        currentLocation.setValue(currLocIndex);
    }
}