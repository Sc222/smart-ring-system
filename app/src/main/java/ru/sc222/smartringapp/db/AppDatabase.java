package ru.sc222.smartringapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.sc222.smartringapp.R;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;


//todo device class
@Database(entities = {Action.class, Location.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase ourInstance = null;

    public static AppDatabase getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = buildDatabase(context);
        }
        return ourInstance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "my-database")
                .addCallback(callback).build();
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        public void onCreate (SupportSQLiteDatabase db) {
            // todo prepopulate here
            Log.e("oncreate","prepopulate");

            Action[] actions = new Action[]{
                    new Action("Поставить чайник"),
                    new Action("Включить свет в прихожей"),
                    new Action("Включить свет в гостиной"),
                    new Action("Включить свет в спальне"),
                    new Action("Выключить свет в прихожей"),
                    new Action("Выключить свет в гостиной"),
                    new Action("Выключить свет в спальне"),
                    new Action("Включить телевизор"),
                    new Action("Выключить телевизор"),
                    new Action("Включить компьютер"),
                    new Action("Выключить компьютер"),
            };
            for (Action action:actions) {
                InsertAction(db, action);
            }

            InsertOutsideLocation(db);
        }
        public void onOpen (SupportSQLiteDatabase db) {
            // do something every time database is open
        }
    };

    //todo refactor
    private static void InsertAction(SupportSQLiteDatabase db, Action action) {
        ContentValues cv = new ContentValues();
        cv.put("actionName",action.actionName);
        db.insert("Action",CONFLICT_IGNORE,cv);
    }

    private static void InsertOutsideLocation(SupportSQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put("locationName","На улице");
        cv.put("locationAddress","Вне других локаций");
        cv.put("locationBackground", R.drawable.location_outside_bg);
        cv.put("singleClickAction", Action.NOT_DEFINED);
        cv.put("doubleClickAction",Action.NOT_DEFINED);
        cv.put("tripleClickAction",  Action.NOT_DEFINED);
        cv.put("longClickAction", Action.NOT_DEFINED);

        db.insert("Location",CONFLICT_IGNORE,cv);
    }


    public abstract ActionDao actionDao();

    public abstract LocationDao locationDao();
}
