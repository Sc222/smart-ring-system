package ru.sc222.smartringapp.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.Location;

public class LocationUtils {

    public static void drawCircle(Context c, GoogleMap mMap, LatLng location, long CircleRadius) {
        CircleOptions options = new CircleOptions();
        options.center(location);
        options.radius(CircleRadius);
        options.fillColor(c.getResources().getColor(R.color.colorPrimaryTransparent));
        options.strokeColor(c.getResources().getColor(R.color.colorPrimaryDarkTransparent));
        options.strokeWidth(10);
        mMap.addCircle(options);
    }

    public static String getLocationString(Context c, LatLng location) {
        Geocoder geocoder = new Geocoder(c, Locale.getDefault());

        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0).getAddressLine(0);
        }
        return c.getResources().getString(R.string.unknown_address);
    }

    public static int getCurrentLocation(List<Location> locations, android.location.Location current) {
        for (int i = 1; i < locations.size(); i++) {//i=1 cause we skip street location
            float[] distance = new float[2];
            Location location = locations.get(i);
            android.location.Location.distanceBetween(current.getLatitude(), current.getLongitude(),
                    location.locationLatitude, location.locationLongitude, distance);
            if (distance[0] < location.radius)
                return i;
        }
        return 0;//!!! TODO locations 0 is always STREET LOCATION
    }

    public static String getLocationMapsLink(Context c, double latitude, double longitude) {
        return c.getString(R.string.google_maps_location_link) + latitude + "," + longitude;
    }

    public static boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null) //no location permission
            return false;
        boolean gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps_enabled || network_enabled;
    }
}
