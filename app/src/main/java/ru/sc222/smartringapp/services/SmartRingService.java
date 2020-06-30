package ru.sc222.smartringapp.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.Observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanCallback;
import no.nordicsemi.android.support.v18.scanner.ScanResult;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;
import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.ble.ButtonBleManager;
import ru.sc222.smartringapp.utils.PreferenceUtils;
import ru.sc222.smartringapp.viewmodels.BleServiceSharedViewModel;

public class SmartRingService extends Service {

    private static final String TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE";
    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";
    public static final String ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE";
    public static final String CHANNEL_NAME = "smart_ring_channel";
    public static final String CHANNEL_DESCRIPTION = "Smart Ring notification channel";

    private static final String NOTIFICATION_CHANNEL_ID = "notification_ble";
    private static final int NOTIFICATION_ID = 1337;
    private static final int BUFFER_CLEAR_FREQUENCY = 10;
    private int bufferCount = 0;


    //TODO USE VIEWMODEL
    //address and device
    private Map<String, BluetoothDevice> devices = new HashMap<>();
    private HashSet<String> devicesBuffer = new HashSet<>();//to clear unvisible devices

    private ButtonBleManager buttonBleManager;
    private String[] buttonStates = { //TODO ВРЕМЕННЫЙ ПОЗОР
            "Обычное нажатие",
            "Двойное нажатие",
            "Длинное нажатие"
    };
    private boolean isScannerStarted = false;
    private BleServiceSharedViewModel bleServiceSharedViewModel;

    //for binding
    private final IBinder binder = new BleServiceBinder();

    public class BleServiceBinder extends Binder {
        public SmartRingService getService() {
            // Return this instance of LocalService so clients can call public methods
            return SmartRingService.this;
        }
    }

    public BleServiceSharedViewModel getViewModel() {
        return bleServiceSharedViewModel;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("service", "oncreate");
        bleServiceSharedViewModel = new BleServiceSharedViewModel(getApplicationContext());
        buttonBleManager = new ButtonBleManager(getApplicationContext());
        buttonBleManager.getButtonState().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer > 0 && integer < 4
                ) {
                    Log.e("ble", "ble btn state:" + buttonStates[integer - 1]);
                } else {
                    Log.e("ble", "ble btn state:" + "Состояние неизвестно");
                }
                //Toast.makeText(getApplicationContext(),"CLICKED: "+integer,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(final int callbackType, @NonNull final ScanResult result) {
        }

        @Override
        public void onBatchScanResults(@NonNull final List<ScanResult> results) {
            bufferCount++;
            if (bufferCount == BUFFER_CLEAR_FREQUENCY) {
                bufferCount = 0;
                devicesBuffer.clear();
            }

            for (ScanResult result : results) {
                devicesBuffer.add(result.getDevice().getAddress());
                BluetoothDevice device = result.getDevice();
                if (!devices.containsKey(device.getAddress())) {
                    devices.put(device.getAddress(), device);
                }
            }

            for (Iterator<Map.Entry<String, BluetoothDevice>> it = devices.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, BluetoothDevice> entry = it.next();
                if (!devicesBuffer.contains(entry.getKey()))
                    it.remove();
            }

            //update model for application communication
            bleServiceSharedViewModel.setDevicesMap(devices);
            //Log.e("ble","scan accomplished: "+results.size());
            connectToDevice();//todo is connected bool
            //todo disconnect when changed device
        }

        @Override
        public void onScanFailed(final int errorCode) {
            //Toast.makeText(getApplicationContext(), "SCANNING FAILED", Toast.LENGTH_SHORT).show();
        }
    };

    private void startScanning() {
        if (isScannerStarted)
            return;
        isScannerStarted = true;
        ScanSettings settings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .setReportDelay(500)
                .setUseHardwareBatchingIfSupported(true)
                .build();
        BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();
        scanner.startScan(null, settings, scanCallback);
    }

    //used by binder
    public void connectToDevice() {
        String savedDeviceAddress = PreferenceUtils.getCurrentDeviceAddress(this);
        //Log.e("saved",savedDeviceAddress);
        if (devices.containsKey(savedDeviceAddress)) {
            Log.e("ble", "device found");
            BluetoothDevice device = devices.get(savedDeviceAddress);
            assert device != null;
            if (!device.getName().equals("Smart Ring")) {
                //wrong device
                Log.e("ble", "wrong device");
                return;
            }
            buttonBleManager.connect(device)
                    .retry(3, 100)
                    .useAutoConnect(false)
                    .enqueue();
        } else {
            //todo replace with notifications
            //Log.e("ble", "device not found");
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null)

                switch (action) {
                    case ACTION_START_FOREGROUND_SERVICE:
                        startForegroundService();
                        Toast.makeText(getApplicationContext(), "Foreground service is started.", Toast.LENGTH_LONG).show();
                        break;
                    case ACTION_STOP_FOREGROUND_SERVICE:
                        stopForegroundService();
                        Toast.makeText(getApplicationContext(), "Foreground service is stopped.", Toast.LENGTH_LONG).show();
                        break;
                }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void startForegroundService() {
        MakeFirstNotificationSetup();
        Notification notification = CreateServiceNotification("Device not connected");
        Notify(notification);
        startForeground(NOTIFICATION_ID, notification);
        startScanning(); //todo restart scanning when location turned on
    }

    private void Notify(Notification notification) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void MakeFirstNotificationSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESCRIPTION);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Notification CreateServiceNotification(String contentText) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_device_24dp)
                .setContentTitle("Smart ring service")
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        return builder.build();
    }

    private void stopForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Stop foreground service.");
        stopForeground(true);
        stopSelf();
    }
}