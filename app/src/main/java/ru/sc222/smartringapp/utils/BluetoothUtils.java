package ru.sc222.smartringapp.utils;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;

import java.util.Map;

import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanCallback;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;
import ru.sc222.smartringapp.ble.ButtonBleManager;


public class BluetoothUtils {

    private static final int REPORT_DELAY = 1000; //milliseconds

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

    public static boolean startScanning(boolean isScannerStarted, ScanCallback scanCallback) {
        if (isScannerStarted)
            return true;

        ScanSettings settings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .setReportDelay(BluetoothUtils.REPORT_DELAY)
                .setUseHardwareBatchingIfSupported(true)
                .build();
        BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();

        //todo set scanner filters to decrease power consumption
        scanner.startScan(null, settings, scanCallback);
        return true;
    }

    //used by binder
    public static void connectToDevice(Map<String, BluetoothDevice> devices, ButtonBleManager buttonBleManager, Context context) {
        String savedDeviceAddress = PreferenceUtils.getCurrentDeviceAddress(context);
        //Log.e("saved",savedDeviceAddress);
        if (devices.containsKey(savedDeviceAddress)) {
            //Log.e("ble", "device found");
            BluetoothDevice device = devices.get(savedDeviceAddress);
            assert device != null;
            if (!device.getName().equals("Smart Ring")) {
                //wrong device
                Log.e("ble", "wrong device");
                return;
            }

            //todo connected and disconnected requests
            buttonBleManager.connect(device)
                    .retry(3, 100)
                    .useAutoConnect(false)
                    .enqueue();
        } else {
            //todo replace with notifications
            //Log.e("ble", "device not found");
        }

    }
}
