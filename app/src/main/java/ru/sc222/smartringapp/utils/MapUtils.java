package ru.sc222.smartringapp.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ru.sc222.smartringapp.R;

public class MapUtils {

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
        Geocoder geocoder = new Geocoder( c, Locale.getDefault());

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
}
