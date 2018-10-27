package com.veronikabogdanovich.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NotificationManager nm;
    private final int NOTIFICATION_ID = 127;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        TextView headerView = (TextView) findViewById(R.id.header);
        switch(id){
            case R.id.action_settings :
                headerView.setText("Это");
                return true;
            case R.id.open_settings:
                headerView.setText("мое");
                return true;
            case R.id.save_settings:
                headerView.setText("меню");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        Toast toast = Toast.makeText(this, "Я смогла!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 600);
        toast.show();
    }


    public void showDialog(View v) {
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }


    public void ChooseFragment(View view) {
        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.button:
                fragment = new FirstFragment();
                break;
            case R.id.button2:
                fragment = new SecondFragment();
                break;
        }

        FragmentManager fa = getSupportFragmentManager();
        FragmentTransaction fr = fa.beginTransaction();
        fr.replace(R.id.fr_place, fragment);
        fr.commit();
    }

    public void showNotification(View view) {
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_launcher_foreground))
                .setTicker("Ку-ку")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Я тут")
                .setContentText("Тык");
        Notification notification = builder.build();
        nm.notify(NOTIFICATION_ID, notification);

    }
}
