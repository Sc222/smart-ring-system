package ru.sc222.smartringapp.db.tasks;

import android.os.AsyncTask;

import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.viewmodels.AddOrEditCommandViewModel;

public class AddActionDbSaver extends AsyncTask<Action, Integer, Boolean> {

    private AddOrEditCommandViewModel model;
    private AppDatabase db;

    public AddActionDbSaver(AddOrEditCommandViewModel model, AppDatabase db) {
        this.model = model;
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Action... actions) {
        db.actionDao().insertAll(actions);
        return true;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        model.setIsCommandAdded(true);
    }
}
