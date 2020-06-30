package ru.sc222.smartringapp.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

import ru.sc222.smartringapp.R;

public class SmsUtils {
    public static void sendSMS(final Context c, String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        PendingIntent sentPI = PendingIntent.getBroadcast(c, 0,
                new Intent(SENT), 0);

        c.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        String msg = c.getString(R.string.alert_has_been_sent);
                        NotificationUtils.showNotification(c, msg, msg);
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        //todo debug, replace with notification
                        Toast.makeText(c, "Error sending message",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        /* broadcast receiver for DELIVERED INTENT
        String DELIVERED = "SMS_DELIVERED";
        PendingIntent deliveredPI = PendingIntent.getBroadcast(c, 0,
                new Intent(DELIVERED), 0);
        c.registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));*/
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, null);
    }
}
