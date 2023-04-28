package com.example.mobdev6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mobdev6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "Donut channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("gg","click");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.donut)
                        .setContentTitle("Важная информация")
                        .setContentText("Пончики - лучшая еда")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"donut", NotificationManager.IMPORTANCE_DEFAULT);
                channel.enableLights(true);
                channel.setShowBadge(true);
                notificationManagerCompat.createNotificationChannel(channel);
                builder.setChannelId(CHANNEL_ID);
                notificationManagerCompat.notify(NOTIFY_ID, builder.build());
                startService(new Intent(MainActivity.this,OverlayService.class));
            }
        });
        setContentView(binding.getRoot());
    }
}