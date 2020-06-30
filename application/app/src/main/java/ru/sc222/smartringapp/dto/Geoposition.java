package ru.sc222.smartringapp.dto;

import com.google.android.gms.maps.model.LatLng;

public class Geoposition {
    private int radius;
    private String address;
    private double latitude;
    private double longitude;

    public Geoposition(int radius, String address, LatLng latLng) {
        this.radius = radius;
        this.address = address;
        latitude = latLng.latitude;
        longitude = latLng.longitude;
    }

    public int getRadius() {
        return radius;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
