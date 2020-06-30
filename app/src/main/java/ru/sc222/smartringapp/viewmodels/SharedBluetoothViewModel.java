package ru.sc222.smartringapp.viewmodels;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.sc222.smartringapp.dto.AdapterBluetoothDevice;
import ru.sc222.smartringapp.utils.PreferenceUtils;

public class SharedBluetoothViewModel extends ViewModel {

    private MutableLiveData<Map<String, BluetoothDevice> > devicesMap;

    //stored as usual list to make recycler adapter work
    private ArrayList<AdapterBluetoothDevice> devicesList = new ArrayList<>();
    private Context c;

    public SharedBluetoothViewModel(Context c) {
        this.c=c;
        devicesMap = new MutableLiveData<>();
        devicesMap.setValue(new HashMap<>());
    }

    public ArrayList<AdapterBluetoothDevice> getDevicesList() {
        return devicesList;
    }

    public LiveData<Map<String, BluetoothDevice>> getDevicesMap() {
        return devicesMap;
    }

    public void setDevicesMap(Map<String, BluetoothDevice> bluetoothDevicesMap) {
        setDevicesList(bluetoothDevicesMap);
        this.devicesMap.setValue(bluetoothDevicesMap);
    }

    private void setDevicesList(Map<String, BluetoothDevice> bluetoothDevicesMap) {
        devicesList.clear();

        String currentDeviceName = PreferenceUtils.getCurrentDeviceName(c);
        String currentDeviceAddress=PreferenceUtils.getCurrentDeviceAddress(c);

        if(!bluetoothDevicesMap.containsKey(currentDeviceAddress))
        {
            devicesList.add(new AdapterBluetoothDevice(currentDeviceAddress,currentDeviceName));
        }

        for(BluetoothDevice device:bluetoothDevicesMap.values())
        {
            devicesList.add(new AdapterBluetoothDevice(device.getAddress(),device.getName()));
        }
    }
}