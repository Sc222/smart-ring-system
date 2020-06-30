package ru.sc222.smartringapp.ui.fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.db.tasks.LocationsDbLoader;
import ru.sc222.smartringapp.services.SmartRingService;
import ru.sc222.smartringapp.ui.activities.AddLocationActivity;
import ru.sc222.smartringapp.utils.LocationUtils;
import ru.sc222.smartringapp.viewmodels.BleServiceSharedViewModel;
import ru.sc222.smartringapp.viewmodels.LocationsViewModel;

import static android.content.Context.LOCATION_SERVICE;

public class LocationsFragment extends Fragment {

    private LocationsViewModel locationsViewModel;
    private android.location.Location currentCoordinates;
    private List<Location> allLocations;
    private boolean isServiceBound = false;

    //TODO !!! узнавать, когда gps отключен и в таком случае выставлять локацию НА УЛИЦЕ
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            currentCoordinates = location;
            if (allLocations != null) {

                int currLocIndex = LocationUtils.getCurrentLocation(allLocations, currentCoordinates);
                //Toast.makeText(getContext(),"Current loc: "+allLocations.get(currLocIndex).locationName,Toast.LENGTH_SHORT).show();
                locationsViewModel.setCurrentLocation(currLocIndex); //todo redraws whole list, FIX LATER
            }
            Log.e("curr loc", "loc: " + location.getLatitude() + " " + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };


    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            SmartRingService.BleServiceBinder binder = (SmartRingService.BleServiceBinder) service;
            SmartRingService smartRingService = binder.getService();
            isServiceBound = true;
            BleServiceSharedViewModel bleServiceSharedViewModel = smartRingService.getViewModel();


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
        locationsViewModel =
                ViewModelProviders.of(this).get(LocationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_locations, container, false);

        LocationManager mLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1 * 1000,
                0, mLocationListener); //раз в 10 секунд
        FloatingActionButton fabAdd = root.findViewById(R.id.fab_add);
        Log.e("fragment", "RELOAD DATA");
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddLocationActivity.class);
                startActivity(intent);
            }
        });

        //todo replace with recyclerlayout in the future
        final LinearLayoutCompat cardContainer = root.findViewById(R.id.card_container);


        //todo redraws whole list, FIX LATER
        locationsViewModel.getCurrentLocation().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer currentLocation) {
                redrawLocationCards(locationsViewModel.getLocations().getValue(), currentLocation, cardContainer);
            }
        });

        locationsViewModel.getLocations().observe(getViewLifecycleOwner(), new Observer<List<Location>>() {
            @Override
            public void onChanged(@Nullable List<Location> locations) {
                allLocations = locations;
                redrawLocationCards(locations, locationsViewModel.getCurrentLocation().getValue(), cardContainer);
            }
        });

        LocationsDbLoader locationsDbLoader = new LocationsDbLoader(locationsViewModel, AppDatabase.getInstance(getContext()));
        locationsDbLoader.execute();

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