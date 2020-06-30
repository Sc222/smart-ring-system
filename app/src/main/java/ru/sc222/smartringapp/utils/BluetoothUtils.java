package ru.sc222.smartringapp.utils;


import android.bluetooth.BluetoothAdapter;
import android.util.Log;


public class BluetoothUtils {

    //todo use broadcast receiver
    public static void enableBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }
    }

    public static boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter.isEnabled();
    }
}
