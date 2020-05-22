package ru.sc222.smartringapp.ui.locations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ru.sc222.smartringapp.db.Location;

public class LocationsViewModel extends ViewModel {

    private MutableLiveData<List<Location>> locations;

    public LocationsViewModel() {

        locations= new MutableLiveData<>();
        locations.setValue(new ArrayList<Location>());
    }


    public void setLocations(List<Location> locations) {
        this.locations.setValue(locations);
    }

    public LiveData<List<Location>> getLocations() {
        return locations;
    }
}