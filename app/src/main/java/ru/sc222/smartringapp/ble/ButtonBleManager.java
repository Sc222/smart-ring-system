package ru.sc222.smartringapp.ble;


import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.UUID;

import no.nordicsemi.android.ble.callback.DataReceivedCallback;
import no.nordicsemi.android.ble.data.Data;
import no.nordicsemi.android.ble.livedata.ObservableBleManager;
import no.nordicsemi.android.log.LogSession;

public class ButtonBleManager extends ObservableBleManager {
    /** Nordic Blinky Service UUID. */
    public final static UUID LBS_UUID_SERVICE = UUID.fromString("00001523-1212-efde-1523-785feabcd123");
    /** BUTTON characteristic UUID. */
    private final static UUID LBS_UUID_BUTTON_CHAR = UUID.fromString("00001524-1212-efde-1523-785feabcd123");

    private final MutableLiveData<Integer> buttonState = new MutableLiveData<>();

    private BluetoothGattCharacteristic buttonCharacteristic;
    private LogSession logSession;
    private boolean supported;

    public ButtonBleManager(@NonNull final Context context) {
        super(context);
        buttonState.setValue(-1);
    }

    public final LiveData<Integer> getButtonState() {
        return buttonState;
    }

    @NonNull
    @Override
    protected BleManagerGattCallback getGattCallback() {
        return new SimpleBleManagerGattCallback();
    }


    @Override
    protected boolean shouldClearCacheWhenDisconnected() {
        return !supported;
    }

    /**
     * The Button callback will be notified when a notification from Button characteristic
     * has been received, or its data was read.
     * <p>
     * If the data received are valid (single byte equal to 0x00 or 0x01), the
     * {@link ButtonDataCallback#onButtonStateChanged} will be called.
     * Otherwise, the {@link ButtonDataCallback#onInvalidDataReceived(BluetoothDevice, Data)}
     * will be called with the data received.
     */
    private	final DataReceivedCallback buttonCallback = new DataReceivedCallback() {
        @Override
        public void onDataReceived(@NonNull BluetoothDevice device, @NonNull Data data) {
            if (data.size() != 1) {
                Log.e("ble","invalid data received");
                return;
            }
            final int state = data.getIntValue(Data.FORMAT_UINT8, 0);
            Log.e("test","received value: "+state);
            buttonState.setValue(state);
        }
    };


    /**
     * BluetoothGatt callbacks object.
     */
    private class SimpleBleManagerGattCallback extends BleManagerGattCallback {
        @Override
        protected void initialize() {
            setNotificationCallback(buttonCharacteristic).with(buttonCallback);
            readCharacteristic(buttonCharacteristic).with(buttonCallback).enqueue();
            enableNotifications(buttonCharacteristic).enqueue();
        }

        @Override
        public boolean isRequiredServiceSupported(@NonNull final BluetoothGatt gatt) {
            final BluetoothGattService service = gatt.getService(LBS_UUID_SERVICE);
            if (service != null) {
                buttonCharacteristic = service.getCharacteristic(LBS_UUID_BUTTON_CHAR);
            }

            supported = buttonCharacteristic != null;
            return supported;
        }

        @Override
        protected void onDeviceDisconnected() {
            buttonCharacteristic = null;
        }
    }
}

