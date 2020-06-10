package ru.sc222.smartringapp.ui.locations;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.sc222.smartringapp.AddLocationActivity;
import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.utils.MapUtils;

import static android.content.Context.LOCATION_SERVICE;

public class LocationsFragment extends Fragment {

    private LocationsViewModel locationsViewModel;
    private android.location.Location currentCoordinates;
    private List<Location>allLocations;

    //TODO !!! simple way of getting current location, ПОСЛЕ ЗАЩИТЫ ЗАПИХАТЬ В СЕРВИС
    //TODO !!! узнавать, когда gps отключен и в таком случае выставлять локацию НА УЛИЦЕ
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            currentCoordinates =location;
            if(allLocations!=null)
            {

                int currLocIndex = MapUtils.getCurrentLocation(allLocations, currentCoordinates);
                //Toast.makeText(getContext(),"Current loc: "+allLocations.get(currLocIndex).locationName,Toast.LENGTH_SHORT).show();
                locationsViewModel.setCurrentLocation(currLocIndex); //todo redraws whole list, FIX LATER
            }
            Log.e("curr loc","loc: "+location.getLatitude()+" "+location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }

        @Override
        public void onProviderEnabled(String provider) { }

        @Override
        public void onProviderDisabled(String provider) { }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        locationsViewModel =
                ViewModelProviders.of(this).get(LocationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_locations, container, false);

        //TODO !!! simple way of getting current location, ПОСЛЕ ЗАЩИТЫ ЗАПИХАТЬ В СЕРВИС
        LocationManager mLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1 * 1000,
                    0, mLocationListener); //раз в 10 секунд, 2 метра
        }else
        {
            //TODO !!! request permission
        }
        FloatingActionButton fabAdd = root.findViewById(R.id.fab_add);
        Log.e("fragment", "RELOAD DATA");
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddLocationActivity.class);
                startActivity(intent);
                // AddLocationFragment bottomSheetFragment = new AddLocationFragment();
                // bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        //todo replace with recyclerlayout in the future
        final LinearLayoutCompat cardContainer = root.findViewById(R.id.card_container);



        //todo redraws whole list, FIX LATER
        locationsViewModel.getCurrentLocation().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer currentLocation) {
                redrawLocationCards(locationsViewModel.getLocations().getValue(),currentLocation,cardContainer);
            }
        });

        locationsViewModel.getLocations().observe(getViewLifecycleOwner(), new Observer<List<Location>>() {
            @Override
            public void onChanged(@Nullable List<Location> locations) {
                allLocations=locations;
                redrawLocationCards(locations,locationsViewModel.getCurrentLocation().getValue(),cardContainer);
            }
        });

        LocationsDbLoader locationsDbLoader = new LocationsDbLoader(locationsViewModel, AppDatabase.getInstance(getContext()));
        locationsDbLoader.execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //todo чисто для защиты, удалить потом
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "smartring";
                    String description = "smart ring notification channel";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel channel = new NotificationChannel("channelid", name, importance);
                    channel.setDescription(description);
                    // Register the channel with the system; you can't change the importance
                    // or other notification behaviors after this
                    NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }




                //todo чисто для защиты, удалить потом
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "channelid")
                        .setSmallIcon(R.drawable.ic_ok_24dp)
                        .setContentTitle("Кнопка нажата!")
                        .setContentText("Свет в спальне включен")
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
                builder.build();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
                notificationManager.notify(122310, builder.build());
                Log.e("notification","didnt show");


            }
        },2000);

        return root;
    }

    //todo СДЕЛАТЬ ПОСЛЕ ЗАЩИТЫ БОЛЕЕ ИЗЯЩНЫЙ МЕТО
    private void redrawLocationCards(List<Location>locations,int currentLocation,LinearLayoutCompat container) {
        Log.e("locations updated","!!!UPDATED!!!");

        container.removeAllViews();
        for (int i=0;i<locations.size();i++) {
            Location loc =locations.get(i);
            View card = LayoutInflater.from(getContext()).inflate(R.layout.card_location, container, false);
            AppCompatImageView imageView=card.findViewById(R.id.location_bg);
            AppCompatTextView textViewName=card.findViewById(R.id.text_name);
            AppCompatTextView textViewCurrentLocation=card.findViewById(R.id.text_current_location);
            AppCompatTextView textViewAddress=card.findViewById(R.id.text_address);
            AppCompatTextView textViewCommandsCount=card.findViewById(R.id.text_commands_count);
            imageView.setImageResource(Location.backgrounds[loc.locationBackground]);
            textViewName.setText(loc.locationName);
            textViewAddress.setText(loc.locationAddress);
            if(i==currentLocation)
            {
                textViewCurrentLocation.setVisibility(View.VISIBLE);
                textViewAddress.setVisibility(View.GONE);
            }

            if(loc.getCommandsCount()==0) //todo workaround remove later
                textViewCommandsCount.setText(String.format("%d команд", loc.getCommandsCount()));
            else
                textViewCommandsCount.setText(String.format(getString(R.string.format_commands_count), loc.getCommandsCount()));
            container.addView(card);
        }
    }
}