package ru.sc222.smartringapp;

import android.os.AsyncTask;

import java.util.List;

import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.ui.commands.CommandsViewModel;

public class AddLocationDbLoader extends AsyncTask<String,Integer,List<Action>> {

    private AddLocationViewModel model;
    private AppDatabase db;

    public AddLocationDbLoader(AddLocationViewModel model, AppDatabase db)
    {
        this.model=model;
        this.db=db;
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
