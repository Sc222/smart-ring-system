package ru.sc222.smartringapp;

import android.os.AsyncTask;

import java.util.List;

import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.ui.commands.CommandsViewModel;

public class AddLocationDbSaver extends AsyncTask<Location,Integer,Boolean> {

    private AddLocationViewModel model;
    private AppDatabase db;

    public AddLocationDbSaver(AddLocationViewModel model, AppDatabase db)
    {
        this.model=model;
        this.db=db;
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
