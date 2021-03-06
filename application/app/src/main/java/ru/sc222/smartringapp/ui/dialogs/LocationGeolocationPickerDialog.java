package ru.sc222.smartringapp.ui.dialogs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.dto.Geoposition;
import ru.sc222.smartringapp.utils.LocationUtils;
import ru.sc222.smartringapp.viewmodels.AddOrEditLocationViewModel;

public class LocationGeolocationPickerDialog extends DialogFragment
        implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private static final int MIN_RADIUS = 100;
    private Context c;
    private AddOrEditLocationViewModel addOrEditLocationViewModel;
    private int radius = MIN_RADIUS;
    private String locationName = "";
    private GoogleMap mMap;
    private MarkerOptions geopositionMarker;
    private AppCompatTextView textViewLocationName;
    private FloatingActionButton fab;

    public LocationGeolocationPickerDialog(Context c, AddOrEditLocationViewModel addOrEditLocationViewModel) {
        this.c = c;
        this.addOrEditLocationViewModel = addOrEditLocationViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_geolocation_dialog, container, false);
        textViewLocationName = rootView.findViewById(R.id.textview_location_name);
        final AppCompatTextView textViewRadius = rootView.findViewById(R.id.textview_radius);
        textViewRadius.setText(String.format(c.getResources().getString(R.string.location_radius), radius));
        AppCompatSeekBar seekBarRadius = rootView.findViewById(R.id.seekbar_radius);
        seekBarRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                radius = progress + MIN_RADIUS;
                textViewRadius.setText(String.format(c.getResources().getString(R.string.location_radius), radius));
                if (geopositionMarker != null)
                    UpdateLocationIndicator(geopositionMarker.getPosition());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            if (geopositionMarker != null) {
                addOrEditLocationViewModel.setGeoposition(new Geoposition(radius, locationName, geopositionMarker.getPosition()));
                dismiss();
            } else
                Snackbar.make(fab, R.string.choose_location, Snackbar.LENGTH_SHORT).show();
        });


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentByTag("mapFragment");
        if (mapFragment == null) {
            mapFragment = new SupportMapFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.map_fragment, mapFragment, "mapFragment");
            ft.commit();
            fm.executePendingTransactions();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(false);
        } else {
            //!!!TODO REQUEST PERMISSIONS ON RESULT CODE!!!
            ActivityCompat.requestPermissions((Activity) c, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        geopositionMarker = new MarkerOptions().position(latLng);
        locationName = LocationUtils.getLocationString(c, latLng);
        textViewLocationName.setText(locationName);
        UpdateLocationIndicator(latLng);
    }

    private void UpdateLocationIndicator(LatLng latLng) {
        mMap.clear();
        mMap.addMarker(geopositionMarker);
        LocationUtils.drawCircle(c, mMap, latLng, radius);
    }

    //todo remove permissions request from here
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (permissions.length == 1 &&
                permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            final Snackbar snackBar = Snackbar.make(fab, R.string.permission_denied, Snackbar.LENGTH_INDEFINITE);
            snackBar.setAction(getString(R.string.ok), v -> {
                snackBar.dismiss();
                dismiss();
            });
            snackBar.show();
        }
    }

}