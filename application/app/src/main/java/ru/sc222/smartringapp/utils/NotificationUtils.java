package ru.sc222.smartringapp.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import ru.sc222.smartringapp.R;

public class NotificationUtils {
    private static final String CHANNEL_NAME = "smart_ring_channel";
    private static final String CHANNEL_DESCRIPTION = "Smart Ring notification channel";
    private static final String NOTIFICATION_CHANNEL_ID = "notification_ble";
    public static final int FOREGROUND_NOTIFICATION_ID = 1337;
    public static final int DEFAULT_NOTIFICATION__ID = 1338;

    public static void notify(Notification notification, Context context) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(FOREGROUND_NOTIFICATION_ID, notification);
    }

    public static void makeFirstNotificationSetup(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESCRIPTION);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static Notification createServiceNotification(String contentText, Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_device_24dp)
                .setContentTitle("Smart ring service")
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        return builder.build();
    }

    public static void showNotification(final Context context, final String title, final String msg) {
        //todo удалить потом
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_ok_24dp)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(DEFAULT_NOTIFICATION__ID, builder.build());
        Log.e("notification", "didnt show");

    }
}
