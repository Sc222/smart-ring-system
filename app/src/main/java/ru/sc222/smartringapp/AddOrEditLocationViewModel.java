package ru.sc222.smartringapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ru.sc222.smartringapp.core.Geoposition;
import ru.sc222.smartringapp.db.Action;

public class AddOrEditLocationViewModel extends ViewModel {
    private MutableLiveData<List<Action>> actions;
    private MutableLiveData<Boolean> isLocationAdded; //exit activity when location is added
    private MutableLiveData<Integer> selectedBackground;
    private MutableLiveData<Geoposition> geoposition;

    public AddOrEditLocationViewModel() {
        actions= new MutableLiveData<>();
        actions.setValue(new ArrayList<Action>());
        isLocationAdded=new MutableLiveData<>();
        isLocationAdded.setValue(false);
        selectedBackground = new MutableLiveData<>();
        selectedBackground.setValue(0);
        geoposition = new MutableLiveData<>();
        geoposition.setValue(null);
    }

    public void setActions(List<Action> actions) {
        this.actions.setValue(actions);
    }

    public LiveData<List<Action>> getActions() {
        return actions;
    }

    public void setIsLocationAdded(boolean isLocationAdded) {
        this.isLocationAdded.setValue(isLocationAdded);
    }

    public LiveData<Boolean> getIsLocationAdded() {
       return isLocationAdded;
    }

    public MutableLiveData<Integer> getSelectedBackground() {
        return selectedBackground;
    }

    public void setSelectedBackground(int background) {
        selectedBackground.setValue(background);
    }

    public MutableLiveData<Geoposition> getGeoposition() {
        return geoposition;
    }

    public void setGeoposition(Geoposition geoposition) {
        this.geoposition.setValue(geoposition);
    }
}