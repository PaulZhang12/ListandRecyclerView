package com.example.classdemo1022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemLanding) {

            Intent landingIntent = new Intent(this, LandingActivity.class);
            startActivity(landingIntent);

        } else if(item.getItemId() == R.id.itemProfile) {

            Intent profileIntent = new Intent(this, ProfileActivity.class);
            startActivity(profileIntent);

        } else if(item.getItemId() == R.id.itemSettings) {

            Toast.makeText(this, "You are already in Settings page, you fool!", Toast.LENGTH_SHORT).show();



        } else if(item.getItemId() == R.id.itemLogout) {

            Intent logoutIntent = new Intent(this, MainActivity.class);
            startActivity(logoutIntent);
        }



        return super.onOptionsItemSelected(item);
    }
}
