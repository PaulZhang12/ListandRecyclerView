package com.example.classdemo1022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

//list view activity
public class LandingActivity extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        listview = (ListView) findViewById(R.id.theList); //linking the listview into the one placed on the landingactivity profile
        //xml

        ArrayList<String> list = new ArrayList<>(); //create a new arraylist to store values in the list
        //you can make an arraylist out of any class, even your own created one, for example a Bird arraylist
        //would just be ArrayList<Bird> list = new ArrayList<>();
        list.add("My name is Paul");  //adding entries to the list, since my list type is String, I'm adding strings
        list.add("Hi, Nice to meet you!");
        list.add("Goodbye, see you later.");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list); //creating a
        //new adapter for the listview. In this case, using ArrayAdapter to link the ArrayList is efficient and simple
        listview.setAdapter(arrayAdapter); //connecting the adapter you just made to your actual listview


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
           Toast.makeText(this, "You are already in Landing page, you fool!", Toast.LENGTH_SHORT).show();

       } else if(item.getItemId() == R.id.itemProfile) {

           Intent profileIntent = new Intent(this, ProfileActivity.class);
           startActivity(profileIntent);

       } else if(item.getItemId() == R.id.itemSettings) {

           Intent settingsIntent = new Intent(this, SettingsActivity.class);
           startActivity(settingsIntent);

       } else if(item.getItemId() == R.id.itemLogout) {

           Intent logoutIntent = new Intent(this, MainActivity.class);
           startActivity(logoutIntent);
       }



        return super.onOptionsItemSelected(item);
    }
}
