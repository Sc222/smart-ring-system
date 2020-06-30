package ru.sc222.smartringapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.ui.dialogs.SelectBluetoothDeviceDialog;
import ru.sc222.smartringapp.utils.PreferenceUtils;

public class DeviceFragment extends Fragment {
    private SelectBluetoothDeviceDialog dialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_device, container, false);

        //todo device activity code here

        if (PreferenceUtils.getCurrentDeviceAddress(getContext()).equals(PreferenceUtils.DEVICE_ADDRESS_DEFAULT)) {
            //show dialog when device is not set
            //dialog = new SelectBluetoothDeviceDialog(false,getActivity(),getActivity().getApplicationContext(),getViewLifecycleOwner());
            //dialog.show();
        }

        return root;
    }

    @Override
    public void onStop() {
        if (dialog != null)
            dialog.dismiss();
        super.onStop();
    }

}