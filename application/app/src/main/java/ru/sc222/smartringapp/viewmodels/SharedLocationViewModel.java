package ru.sc222.smartringapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.Location;

public class SharedLocationViewModel extends ViewModel {

    private MutableLiveData<List<Location>> locations;
    private MutableLiveData<Integer> currentLocation;
    private MutableLiveData<List<Action>> actions;

    public SharedLocationViewModel() {
        locations = new MutableLiveData<>();
        locations.setValue(new ArrayList<>());
        actions = new MutableLiveData<>();
        actions.setValue(new ArrayList<>());
        currentLocation = new MutableLiveData<>();
        currentLocation.setValue(-1);
    }

    public LiveData<List<Action>> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions.setValue(actions);
    }

    public LiveData<List<Location>> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        if (locations != null)
            this.locations.setValue(locations);
    }

    public LiveData<Integer> getCurrentLocation() {
        return currentLocation;
    }

    //todo THIS METHODS CHANGES LOCATIONS LIST AND REDRAWS ALL ACTIVITY LIST, FIX LATER
    public void setCurrentLocation(int currLocIndex) {
        currentLocation.setValue(currLocIndex);
    }
}