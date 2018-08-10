package com.example.user.simpleapp;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ChatActivity extends AppCompatActivity {

    private TextView chatBox;
    private EditText textArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatBox = (TextView)findViewById(R.id.chatBox);
        textArea = (EditText)findViewById(R.id.textArea);
        Button chatBtn = (Button) findViewById(R.id.chatBtn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefUsers = database.getReference("Users");



    }






    public void sendText(View view) {

// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefUsers = database.getReference("Chatbox");

        myRefUsers.setValue("Hello, World!**************************");


        // Read from the database
        myRefUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Wow", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }
}
