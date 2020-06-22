package ru.sc222.smartringapp.ui.device;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.ui.dialogs.SelectBluetoothDeviceDialog;
import ru.sc222.smartringapp.utils.PreferenceUtils;

public class DeviceFragment extends Fragment {
    private  SelectBluetoothDeviceDialog dialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_device, container, false);
        // final CardView mainCard = root.findViewById(R.id.main_card);
        //mainCard.setEnabled(false); //changes main device color tint
        // final TextView textView = root.findViewById(R.id.text_device);
      /*  deviceViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        if(PreferenceUtils.getCurrentDeviceAddress(getContext()).equals(PreferenceUtils.DEVICE_DEF_VALUE))
        {
            dialog = new SelectBluetoothDeviceDialog(false,getActivity(),getActivity().getApplicationContext(),getViewLifecycleOwner());
            dialog.show();
        }

        return root;
    }

    @Override
    public void onStop() {
        Log.e("sdfsdf","asdasd");
        if(dialog!=null)
            dialog.dismiss();
        super.onStop();
    }

}