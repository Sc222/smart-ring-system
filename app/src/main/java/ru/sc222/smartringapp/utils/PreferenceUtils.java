package ru.sc222.smartringapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import ru.sc222.smartringapp.R;

public class PreferenceUtils {

    public static final String NAV_ITEM="current_nav_item";

    public static void saveCurrentNavigationItem(Context context, int itemId)
    {
        Log.e("SAVE: ","item");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(NAV_ITEM,itemId).apply();
    }

    public static int getCurrentNavigationItem(Context context)
    {
        Log.e("GET: ","item");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(NAV_ITEM, R.id.navigation_device);
    }
}
