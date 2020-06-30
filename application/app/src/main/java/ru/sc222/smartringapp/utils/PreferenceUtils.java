package ru.sc222.smartringapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import ru.sc222.smartringapp.R;

public class PreferenceUtils {

    public static final String NAV_ITEM = "current_nav_item";
    public static final String DEVICE_ADDRESS = "device_address";
    public static final String DEVICE_NAME = "device_name";
    public static final String DEVICE_ADDRESS_DEFAULT = "C1:7F:8C:F6:B1:3B";
    public static final String DEVICE_NAME_DEFAULT = "Smart Ring";

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

    public static String getCurrentDeviceAddress(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(DEVICE_ADDRESS, DEVICE_ADDRESS_DEFAULT);
    }

    public static String getCurrentDeviceName(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(DEVICE_NAME, DEVICE_NAME_DEFAULT);
    }

    public static void saveCurrentDevice(Context c, String address, String name) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        sp.edit().putString(DEVICE_ADDRESS, address).apply();
        sp.edit().putString(DEVICE_NAME, name).apply();
    }
}
