package ru.sc222.smartringapp.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import ru.sc222.smartringapp.network.RequestQueueSingleton;

public class NetworkUtils {
    //possible states are listed on the server
    public static final String UPDATE_URL = "https://smart-ring-webhook.glitch.me/update?state=";

    public static void updateButtonState(String state, Context context) {
        StringRequest updateRequest = new StringRequest(Request.Method.GET, UPDATE_URL + state,
                response -> {
                    // Display the first 500 characters of the response string.
                    Log.d("NET", "response: " + response);
                },
                error -> Log.d("NET", "error response: " + error.getMessage()));
        RequestQueueSingleton.getInstance(context).addToRequestQueue(updateRequest);
    }
}
