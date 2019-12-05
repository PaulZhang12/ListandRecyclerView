package com.example.classdemo1022;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Contact> Contacts = new ArrayList<Contact>(); //Creating a new arraylist of contacts for the adapter to display
    private Context mContext; //context variable, for use later on
    private StorageReference mStorageRef; //storage to link to firebase, need to import mstorage

    public RecyclerViewAdapter(List<Contact> Contacts, Context mContext){ //RecyclerViewAdapter constructor, what creates the recyclerViewAdapter
        this.Contacts = Contacts; //Setting the Contacts arraylist created above to the Contacts list passed in
        this.mContext = mContext; //pass in the context of the activity, which is passed into the constructor
        mStorageRef = FirebaseStorage.getInstance().getReference(); //get the storage reference from firebase
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //The code that
        //determines how each view is created
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        //Inflate the view, taking from the user_item layout, which is just the specific xml for a single Contact
        //Check out the user_item layout to see what I'm talking about
        ViewHolder viewHolder = new ViewHolder(view); //Create a new viewholder, need this
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(Contacts.get(position).name);
        //Set the text of the viewholder from the previous function, onCreateViewHolder, to the name of the
        //specific contact by accessing the specific position for that contact. //the textView is from the user_item
        // xml

        final File localFile = Contacts.get(position).profilePic; //getting the profilepic file from Contact
        //with respect to the specific contact you want. Since position is passed into this function, to access
        //the Contact for the position, just do Contacts.get(position)
        StorageReference profileRef = mStorageRef.child(Contacts.get(position).photoTitle); //reference for profile
        //from firebase storage. My images were just in the master folder, and I would just get the exact picture
        //based on the photoTitle, which was determined by the Contacts contructor

        profileRef.getFile(localFile) //accessing the file from firebase storage
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                        try{ //see if this works without an exception
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.fromFile(localFile)); //turn it into an image
                            holder.imageView.setImageBitmap(bitmap); //Setting the imageview in user_item.xml to the picture obtained
                        }
                        catch(IOException e){ //Catching some exception, making it so it doesn't just crash your app

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
            }
        });

    }

    @Override
    public int getItemCount() { //you need this function, just how it works
        return Contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder { //class to hold each individual view to display to your recyclerview
        //basically holds the user_item

        ImageView imageView; //imageView from user_item
        TextView textView; //textView from user_item
        RelativeLayout parentLayout; //references who the parent is and the specific layout of the parent,
        //so you know where the imageView and textView are contained

        public ViewHolder(@NonNull View view){ //ViewHolder constructor
            super(view);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

}
