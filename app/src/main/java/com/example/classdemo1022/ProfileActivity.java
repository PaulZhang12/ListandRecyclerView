package com.example.classdemo1022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


//RecyclerView Activity
public class ProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView; //recycler view variable
    private RecyclerView.LayoutManager layoutManager; //layout manager for recycler view, need this for a recyclerview
    private List<Contact> contacts; //list to store my contacts, ends up being an arraylist
    //In this case, ArrayList<Contact> contacts would also work, but I just generalized a list depending
    //on the type of list i would use in the future

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        contacts = new ArrayList<Contact>(); //Creating a new arraylist for my class contacts
        contacts.add(new Contact("Paul", "paul_image")); //since contact requires a name and an image, I pass a name
        //and photoName into the constructor, which you can view in the Contact class, creating a new contact
        contacts.add(new Contact("Becca", "becca_image"));
        contacts.add(new Contact("Sanjeev", "sanjeev_image"));
        contacts.add(new Contact("Charlie", "charlie_image"));


        recyclerView = findViewById(R.id.recyclerView); //Link recyclerview variable to xml
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(contacts, this); //Linking the adapter to recyclerView,
        //check out the RecyclerViewAdapter (this is the hard part)
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //Setting the layout manager, commonly used is linear
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

            Toast.makeText(this, "You are already in Profile page, you fool!", Toast.LENGTH_SHORT).show();

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
