package com.example.classdemo1022;

import java.io.File;
import java.io.IOException;

public class Contact {
    public String name; //name of contact
    public String photoTitle; //phototitle, eventually extracting from firebase
    public File profilePic; //the profilePic File

    public Contact(String name, String photoTitle){ //Contact constructor
        this.name = name; //set name to name passed in
        this.photoTitle = "images/" + photoTitle + ".jpg"; //create the extension with .jpg. My pictures were under the
        // /images directory, so that's why it's in front
        try{ //See if a file can be created thats blank
            profilePic = File.createTempFile(photoTitle, "jpg"); //Set the profilePic to the temp file
        }
        catch(IOException e){

        }
    }


}
