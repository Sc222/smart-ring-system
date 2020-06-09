package ru.sc222.smartringapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import ru.sc222.smartringapp.R;

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
                    new Action("Тревога","Отправить сообщение родственникам",R.drawable.ic_action_danger),
                    new Action("Развлечения","Включить телевизор",R.drawable.ic_action_tv),
                    new Action("Работа","Включить компьютер",R.drawable.ic_action_pc),
                    new Action("Освещение","Включить свет в прихожей",R.drawable.ic_action_light_1),
                    new Action("Освещение","Включить свет в гостиной",R.drawable.ic_action_light_2),
                    new Action("Освещение","Включить свет в спальне",R.drawable.ic_action_light_3),
                    new Action("Питание","Включить чайник",R.drawable.ic_action_kettle)
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
        cv.put("icon",action.icon);
        db.insert("Action",CONFLICT_IGNORE,cv);
    }

    private static void InsertOutsideLocation(SupportSQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put("locationName","Сад (текущая)");
        cv.put("locationAddress","пер. Замятина, 45, Екатеринбург, Свердловская обл., Россия");
        cv.put("locationBackground", R.drawable.location_green_bg);
        cv.put("singleClickAction", "Включить телевизор");
        cv.put("doubleClickAction", "Включить телевизор"); //todo это чисто для защиты
        cv.put("tripleClickAction",  Action.NOT_DEFINED);
        cv.put("longClickAction", "Включить телевизор");

        db.insert("Location",CONFLICT_IGNORE,cv);





        cv = new ContentValues();
        cv.put("locationName","На улице");
        cv.put("locationAddress","Вне других локаций");
        cv.put("locationBackground", R.drawable.location_outside_bg);
        cv.put("singleClickAction", Action.NOT_DEFINED);
        cv.put("doubleClickAction", "Включить телевизор"); //todo это чисто для защиты
        cv.put("tripleClickAction",  Action.NOT_DEFINED);
        cv.put("longClickAction", Action.NOT_DEFINED);

        db.insert("Location",CONFLICT_IGNORE,cv);

        cv.put("locationName","Дом");
        cv.put("locationAddress","ул. Стачек, 59, Екатеринбург, Свердловская обл., Россия");
        cv.put("locationBackground", R.drawable.location_purple_bg);
        cv.put("singleClickAction", "Включить телевизор");
        cv.put("doubleClickAction", "Включить телевизор"); //todo это чисто для защиты
        cv.put("tripleClickAction",  "Включить телевизор");
        cv.put("longClickAction", "Включить телевизор");

        db.insert("Location",CONFLICT_IGNORE,cv);
    }


    public abstract ActionDao actionDao();

    public abstract LocationDao locationDao();
}
