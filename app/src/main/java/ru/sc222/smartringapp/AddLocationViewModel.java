package ru.sc222.smartringapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ru.sc222.smartringapp.db.Action;

public class AddLocationViewModel extends ViewModel {
    private MutableLiveData<List<Action>> actions;
    private MutableLiveData<Boolean> isLocationAdded; //exit activity when location is added

    public AddLocationViewModel() {
        actions= new MutableLiveData<>();
        actions.setValue(new ArrayList<Action>());
        isLocationAdded=new MutableLiveData<>();
        isLocationAdded.setValue(false);
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
}