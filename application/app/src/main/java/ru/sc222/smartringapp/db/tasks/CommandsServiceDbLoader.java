package ru.sc222.smartringapp.db.tasks;

import android.os.AsyncTask;

import java.util.List;

import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.viewmodels.SharedLocationViewModel;

public class CommandsServiceDbLoader extends AsyncTask<String, Integer, List<Action>> {
    //todo use interfaces and merge "loaders" into one class
    private SharedLocationViewModel model;
    private AppDatabase db;

    public CommandsServiceDbLoader(SharedLocationViewModel model, AppDatabase db) {
        this.model = model;
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Action> doInBackground(String... strings) {
        return db.actionDao().getAll();
    }


    @Override
    protected void onPostExecute(List<Action> actions) {
        super.onPostExecute(actions);
        model.setActions(actions);
    }
}