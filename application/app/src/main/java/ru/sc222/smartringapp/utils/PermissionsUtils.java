package ru.sc222.smartringapp.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionsUtils {
    public static final int REQUEST_CODE = 1337;
    public static final String[] REQUIRED_PERMISSIONS = new String[]
            {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.SEND_SMS
            };

    public static boolean hasRequiredPermissions(Context context) {
        for (String permission : REQUIRED_PERMISSIONS)
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        return true;
    }

    public static String[] getPermissionsToRequest(Context context) {
        List<String> permissionsToRequest = new ArrayList<>();
        for (String permission : REQUIRED_PERMISSIONS)
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                permissionsToRequest.add(permission);
        return permissionsToRequest.toArray(new String[0]);
    }
}
