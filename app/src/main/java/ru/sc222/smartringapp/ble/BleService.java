package ru.sc222.smartringapp.ble;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanCallback;
import no.nordicsemi.android.support.v18.scanner.ScanResult;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;
import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.utils.PreferenceUtils;

public class BleService extends Service {

    private static final String TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE";
    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";
    public static final String ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE";
    public static final String CHANNEL_NAME="smart_ring_channel";
    public static final String CHANNEL_DESCRIPTION="Smart Ring notification channel";

    private static final String NOTIFICATION_CHANNEL_ID="notification_ble";
    private static final int NOTIFICATION_ID=1337;


    //TODO USE VIEWMODEL
    //address and device
    private Map<String,BluetoothDevice> devices = new HashMap<String,BluetoothDevice>();
    private ButtonBleManager buttonBleManager;
    private String[] buttonStates = { //TODO ВРЕМЕННЫЙ ПОЗОР
            "Обычное нажатие",
            "Двойное нажатие",
            "Длинное нажатие"
    };
    private boolean isScannerStarted = false;

    public BleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buttonBleManager = new ButtonBleManager(this);
        buttonBleManager.getButtonState().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer > 0 && integer < 4
                ) {
                    Log.e("ble","ble btn state:"+buttonStates[integer - 1]);
                } else {
                    Log.e("ble","ble btn state:"+"Состояние неизвестно");
                }
                //Toast.makeText(getApplicationContext(),"CLICKED: "+integer,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(final int callbackType, @NonNull final ScanResult result) {
            // This callback will be called only if the scan report delay is not set or is set to 0.
            Toast.makeText(getApplicationContext(), "THIS METHOD SHOULD BE HANDLED", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBatchScanResults(@NonNull final List<ScanResult> results) {
            for (ScanResult result : results) {
                BluetoothDevice device = result.getDevice();
                if (!devices.containsKey(device.getAddress())){
                    devices.put(device.getAddress(),device);
                }
            }
            Log.e("ble","scan accomplished: "+results.size());
            connectToDevice();//todo is connected bool
        }

        @Override
        public void onScanFailed(final int errorCode) {
            Toast.makeText(getApplicationContext(), "SCANNING FAILED", Toast.LENGTH_SHORT).show();
        }
    };

    private void startScanning() {
        if (isScannerStarted)
            return;
        //todo CHECK PERMISSIONS BEFORE SCANNING AND SHOW ERRORS
        if (BleUtils.isLocationPermissionsGranted(this)) {

            isScannerStarted = true;
            ScanSettings settings = new ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .setReportDelay(500)
                    .setUseHardwareBatchingIfSupported(true)
                    .build();
            BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();
            scanner.startScan(null, settings, scanCallback);

        } else {
            Toast.makeText(this, "NO LOCATION PERMISSION", Toast.LENGTH_SHORT).show();
        }
    }

    private void connectToDevice() {
        String savedDeviceAddress=PreferenceUtils.getCurrentDevice(this);
        Log.e("save: ",savedDeviceAddress);
        if(devices.containsKey(savedDeviceAddress))
        {
            Log.e("ble","device found");
            BluetoothDevice device=devices.get(savedDeviceAddress);
            assert device != null;
            buttonBleManager.connect(device)
                    .retry(3, 100)
                    .useAutoConnect(false)
                    .enqueue();
        }
        else {
            //todo replace with notifications
            Log.e("ble","device not found");
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
                    //todo action connect device
                }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void startForegroundService() {
       MakeFirstNotificationSetup();
       Notification notification = CreateServiceNotification("Device not connected");
       Notify(notification);
       startForeground(NOTIFICATION_ID, notification);
       startScanning();
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

    /*@RequiresApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName) {
        Intent resultIntent = new Intent(this, MainActivity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationChannel chan = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setContentIntent(resultPendingIntent) //intent
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notificationBuilder.build());
        startForeground(1, notification);
    }*/


    private void stopForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Stop foreground service.");

        // Stop foreground mode and remove the notification.
        stopForeground(true);

        // Stop the foreground service.
        stopSelf();
    }
}
