package ru.sc222.smartringapp.ui.dialogs;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.ble.BleService;
import ru.sc222.smartringapp.ble.BleServiceSharedViewModel;
import ru.sc222.smartringapp.core.AdapterBluetoothDevice;
import ru.sc222.smartringapp.utils.PreferenceUtils;

//dialog should be dismissed in fragment/activity ondestroy or service wont be unbound
public class SelectBluetoothDeviceDialog extends AlertDialog {

    private final LifecycleOwner lifecycleOwner;
    private boolean isCancelable;
    private Activity activity;
    private Context appContext;
    boolean isServiceBound = false;
    private RecyclerView recyclerView;
    private BluetoothDevicesAdapter adapter;

    public SelectBluetoothDeviceDialog(boolean isCancelable, Activity activity, Context appContext, LifecycleOwner lifecycleOwner) {
        super(activity);
        this.isCancelable = isCancelable;
        this.activity=activity;
        this.appContext=appContext;
        this.lifecycleOwner=lifecycleOwner;
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            BleService.BleServiceBinder binder = ( BleService.BleServiceBinder) service;
            BleService bleService = binder.getService();
            isServiceBound = true;
            BleServiceSharedViewModel bleServiceSharedViewModel = bleService.getViewModel();

            adapter = new BluetoothDevicesAdapter(appContext, bleServiceSharedViewModel.getDevicesList());
            recyclerView.setAdapter(adapter);

            adapter.setClickListener(new BluetoothDevicesAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    AdapterBluetoothDevice item=adapter.getItem(position);
                    PreferenceUtils.saveCurrentDevice(appContext,item.getAddress(),item.getName());
                    dismiss();
                   // unbindService();
                }
            });

            bleServiceSharedViewModel.getDevicesMap().observe(lifecycleOwner, new Observer<Map<String, BluetoothDevice>>() {
                @Override
                public void onChanged(Map<String, BluetoothDevice> deviceMap) {
                    adapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isServiceBound = false;
        }
    };

    @Override
    public void dismiss() {
        super.dismiss();
       // unbindService();
    }

    private void unbindService() {
        appContext.unbindService(connection);
    }

    @Override
    public void show() {
        final View layout = activity.getLayoutInflater().inflate(R.layout.recycler_view_dialog, null);
        setTitle(R.string.select_device_dialog_title);
        setCancelable(isCancelable);
        recyclerView = layout.findViewById(R.id.recyclerViewDialog);
        recyclerView.setLayoutManager(new LinearLayoutManager(appContext,RecyclerView.VERTICAL,false));

        //bind service
        Intent serviceIntent = new Intent(appContext, BleService.class);
        appContext.bindService(serviceIntent,connection,Context.BIND_AUTO_CREATE);
     // .getInstance().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        setView(layout);
        super.show();
    }

}