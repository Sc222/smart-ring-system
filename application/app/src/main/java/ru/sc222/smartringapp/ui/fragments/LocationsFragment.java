package ru.sc222.smartringapp.ui.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.services.SmartRingService;
import ru.sc222.smartringapp.ui.activities.AddLocationActivity;
import ru.sc222.smartringapp.utils.ServiceUtils;
import ru.sc222.smartringapp.viewmodels.SharedLocationViewModel;

public class LocationsFragment extends Fragment {
    private LinearLayoutCompat cardContainer;

    private boolean isServiceBound = false;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            SmartRingService.BleServiceBinder binder = (SmartRingService.BleServiceBinder) service;
            SmartRingService smartRingService = binder.getService();
            isServiceBound = true;
            SharedLocationViewModel sharedLocationViewModel = smartRingService.getLocationViewModel();

            //todo redraws whole list, FIX LATER
            //todo getviewlifecycleowner crashes so i use requireActivity()
            sharedLocationViewModel.getCurrentLocation().observe(requireActivity(), currentLocation -> redrawLocationCards(sharedLocationViewModel.getLocations().getValue(), currentLocation, cardContainer));

            sharedLocationViewModel.getLocations().observe(requireActivity(), locations -> {
                assert locations != null;
                redrawLocationCards(locations, sharedLocationViewModel.getCurrentLocation().getValue(), cardContainer);
            });

            //TODO is used when new locations are added/deleted/edited from loc. fragment (IS IT OK?)
            smartRingService.loadLocationsFromDb();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isServiceBound = false;
        }
    };

    /*private void unbindService() {
        getContext().getApplicationContext().unbindService(connection);
    }*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_locations, container, false);
        FloatingActionButton fabAdd = root.findViewById(R.id.fab_add);
        Log.e("fragment", "RELOAD DATA");
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddLocationActivity.class);
            startActivity(intent);
        });

        //todo replace with recyclerlayout in the future
        cardContainer = root.findViewById(R.id.card_container);

        //bind service
        if (ServiceUtils.isServiceRunning(getContext(), SmartRingService.class)) {
            Intent serviceIntent = new Intent(getContext().getApplicationContext(), SmartRingService.class);
            getContext().getApplicationContext().bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
        }

        // MOCK_UTILS.showNotification(getContext(),2000);
        // String coordinates = MapUtils.getLocationMapsLink(getContext(),57.0804465,60.5845488);
        // final String message = "Тревога!\nАдрес:" +coordinates;
        // MOCK_UTILS.sendMessage(message,2000);
        // new Handler().postDelayed(new Runnable() {
        //     @Override
        //     public void run() {
        //         MOCK_UTILS.sendSMS(getActivity(),"+79222941329",message);
        //     }
        // },3500);

        return root;
    }


    private void redrawLocationCards(List<Location> locations, int currentLocation, LinearLayoutCompat container) {
        Log.e("locations updated", "!!!UPDATED!!!");

        container.removeAllViews();
        for (int i = 0; i < locations.size(); i++) {
            Location loc = locations.get(i);
            View card = LayoutInflater.from(getContext()).inflate(R.layout.card_location, container, false);
            AppCompatImageView imageView = card.findViewById(R.id.location_bg);
            AppCompatTextView textViewName = card.findViewById(R.id.text_name);
            AppCompatTextView textViewCurrentLocation = card.findViewById(R.id.text_current_location);
            AppCompatTextView textViewAddress = card.findViewById(R.id.text_address);
            AppCompatTextView textViewCommandsCount = card.findViewById(R.id.text_commands_count);
            imageView.setImageResource(Location.backgrounds[loc.locationBackground]);
            textViewName.setText(loc.locationName);
            textViewAddress.setText(loc.locationAddress);
            if (i == currentLocation) {
                textViewCurrentLocation.setVisibility(View.VISIBLE);
                textViewAddress.setVisibility(View.GONE);
            }

            if (loc.getCommandsCount() == 0) //todo use countable string resources
                textViewCommandsCount.setText(String.format(getString(R.string.zero_commands), loc.getCommandsCount()));
            else
                textViewCommandsCount.setText(String.format(getString(R.string.format_commands_count), loc.getCommandsCount()));
            container.addView(card);
        }
    }
}