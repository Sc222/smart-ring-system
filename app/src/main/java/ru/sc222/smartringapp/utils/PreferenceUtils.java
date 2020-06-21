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

    //TODO FIX: TEMPORARY NAME USAGE, BUT WE SHOULD USE 6bit address
    public static String getCurrentDevice(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(DEVICE_ADDRESS, "Smart Ring");
    }
   /* public static void saveCurrentDevice(Context context, String deviceId) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(NAV_ITEM, deviceId).apply();
    }

    //
*/
}
