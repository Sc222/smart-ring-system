package ru.sc222.smartringapp.db.tasks;

import android.os.AsyncTask;

import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.viewmodels.AddOrEditLocationViewModel;

public class AddLocationDbSaver extends AsyncTask<Location, Integer, Boolean> {

    private AddOrEditLocationViewModel model;
    private AppDatabase db;

    public AddLocationDbSaver(AddOrEditLocationViewModel model, AppDatabase db) {
        this.model = model;
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Location... locations) {
        db.locationDao().insertAll(locations);
        return true;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        model.setIsLocationAdded(true);
    }
}
