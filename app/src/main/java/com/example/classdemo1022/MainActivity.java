package com.example.classdemo1022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;

//login page
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail, editTextPassword; //edit texts for email and password login
    Button buttonLogin, buttonRegister; //button for logging in and registering

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextEmail = findViewById(R.id.editTextEMail); //linking components to those in xml file
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonLogin.setOnClickListener(this); //setting on click listeners
        buttonRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {



        if(v == buttonRegister) { //if register gets pressed
            makeNewUsers(editTextEmail.getText().toString(), editTextPassword.getText().toString()); //go to the register function below with
            //email and password from the editTexts
        } else if(v == buttonLogin) { //if login gets pressed

            loginUser(editTextEmail.getText().toString(), editTextPassword.getText().toString()); //go to the login function below
        }
    }


    public void makeNewUsers(String email, String password) { //function to register new users, passes in email and password for use
        mAuth.createUserWithEmailAndPassword(email, password) //creating a new user with the email and password passed in
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { //if the task is successful
                          //Display a success message
                            Toast.makeText(MainActivity.this, "User Registration Successful", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void loginUser(String email, String password) { //login with email and password passed in from edittexts
        mAuth.signInWithEmailAndPassword(email, password) //signing in with the email and password
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent mainIntent = new Intent(MainActivity.this, LandingActivity.class);
                            startActivity(mainIntent);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Failed Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
