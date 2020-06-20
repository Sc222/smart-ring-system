package ru.sc222.smartringapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import ru.sc222.smartringapp.R;

public class PreferenceUtils {

    public static final String NAV_ITEM = "current_nav_item";
    public static final String DEVICE_ADDRESS = "device_address";

    public static void saveCurrentNavigationItem(Context context, int itemId) {
        Log.e("SAVE: ", "item");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(NAV_ITEM, itemId).apply();
    }

    public static int getCurrentNavigationItem(Context context) {
        Log.e("GET: ", "item");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(NAV_ITEM, R.id.navigation_device);
    }

   /* public static void saveCurrentDevice(Context context, String deviceId) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(NAV_ITEM, deviceId).apply();
    }

    //TODO FIX: NOW WE ARE USING NAME, BUT WE SHOULD USE
    public static int getCurrentDevice(Context context) {
        Log.e("GET: ", "item");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(NAV_ITEM, "Smart Ring");
    }*/
}
