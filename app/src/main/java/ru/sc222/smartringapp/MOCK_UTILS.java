package ru.sc222.smartringapp;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MOCK_UTILS {
    public static void showNotification(final Context context, int delay, final String msg) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //todo удалить потом
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "smartring";
                    String description = "smart ring notification channel";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel channel = new NotificationChannel("channelid", name, importance);
                    channel.setDescription(description);
                    // Register the channel with the system; you can't change the importance
                    // or other notification behaviors after this
                    NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }




                //todo удалить потом
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelid")
                        .setSmallIcon(R.drawable.ic_ok_24dp)
                        .setContentTitle("Кнопка нажата!")
                        .setContentText(msg)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
                builder.build();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(122310, builder.build());
                Log.e("notification","didnt show");


            }
        },delay);
    }

    public static void sendMessage(final String text,int delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("+79222941329", null, text, null, null);
                Log.e("works&","idk");
            }
        },delay);
        }

    public static void sendSMS(final Context c,String phoneNumber, String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(c, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(c, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        ((Activity)c).registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        MOCK_UTILS.showNotification(c,0,"Тревожное сообщение отправлено");
                        //Toast.makeText(c, "SMS sent",
                        //        Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(c, "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(c, "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(c, "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(c, "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        /*((Activity)c).registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:

                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(c, "Ошибка при отправке сообщения",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));*/

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }
}
