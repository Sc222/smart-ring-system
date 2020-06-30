package ru.sc222.smartringapp.db.tasks;

import android.os.AsyncTask;

import java.util.List;

import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.viewmodels.SharedLocationViewModel;

public class LocationsDbLoader extends AsyncTask<String, Integer, List<Location>> {
    //todo use interfaces and merge "loaders" into one class
    private SharedLocationViewModel model;
    private AppDatabase db;

    public LocationsDbLoader(SharedLocationViewModel model, AppDatabase db) {
        this.model = model;
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Location> doInBackground(String... strings) {
        return db.locationDao().getAll();
    }


    @Override
    protected void onPostExecute(List<Location> locations) {
        super.onPostExecute(locations);
        model.setLocations(locations);
    }
}
