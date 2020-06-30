package ru.sc222.smartringapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;


//todo device class
@Database(entities = {Action.class, Location.class}, version = 1, exportSchema = true)
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
                    new Action("Тревога","Отправить тревожное сообщение отцу",0,Action.TYPE_ALERT,"+78005553535"),
                    new Action("Развлечения","Включить телевизор",1,Action.TYPE_HOME_CONTROL,""),
                    new Action("Работа","Включить компьютер",2,Action.TYPE_HOME_CONTROL,""),
                    new Action("Освещение","Включить свет в прихожей",3,Action.TYPE_HOME_CONTROL,""),
                    new Action("Освещение","Включить свет в гостиной",4,Action.TYPE_HOME_CONTROL,""),
                    new Action("Освещение","Включить свет в спальне",5,Action.TYPE_HOME_CONTROL,""),
                    new Action("Питание","Включить чайник",6,Action.TYPE_HOME_CONTROL,"")
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
        cv.put("actionCategory",action.actionCategory);
        cv.put("actionDescription",action.actionDescription);
        cv.put("actionType",action.actionType);
        cv.put("phoneNumber",action.phoneNumber);
        cv.put("icon",action.icon);
        db.insert("Action",CONFLICT_IGNORE,cv);
    }

    private static void InsertOutsideLocation(SupportSQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put("locationName","На улице");
        cv.put("radius",0);
        cv.put("locationAddress","Вне других локаций");
        cv.put("locationLongitude",0d);
        cv.put("locationLatitude",0d);
        cv.put("locationBackground", 10);
        cv.put("singleClickAction", Action.NOT_DEFINED);
        cv.put("doubleClickAction", "Включить телевизор");
        cv.put("tripleClickAction",  Action.NOT_DEFINED);
        cv.put("longClickAction", Action.NOT_DEFINED);
        db.insert("Location",CONFLICT_IGNORE,cv);
    }

    public abstract ActionDao actionDao();

    public abstract LocationDao locationDao();
}
