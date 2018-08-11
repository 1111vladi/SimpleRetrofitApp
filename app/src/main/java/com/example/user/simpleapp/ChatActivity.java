package com.example.user.simpleapp;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import data.model.TextPost;
import data.remote.APIService;
import data.remote.ApiUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatActivity extends AppCompatActivity {

    private TextView chatBox;
    private EditText textArea;

    private APIService apiService;

    private FirebaseDatabase database;
    private DatabaseReference dbRef;

    private ValueEventListener textListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatBox = (TextView)findViewById(R.id.chatBox);
        textArea = (EditText)findViewById(R.id.textArea);
        Button chatBtn = (Button) findViewById(R.id.chatBtn);

        apiService = ApiUtils.getAPIService(ApiUtils.HTTPUrls.CHAT);

        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("Chatbox");

        // Does nothing at the moment
        prepareFirebaseListeners();

        dbRef.addValueEventListener(textListener);

    }


    // TODO - Finish Method
    private void prepareFirebaseListeners(){
        textListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("Meh", "Failed to read value.", databaseError.toException());
            }
        };

    }


    public void sendText(View view) {

    String textMessage = textArea.getText().toString();
        if (!TextUtils.isEmpty(textMessage)) {
            sendPost(textMessage);

        }
    }
    // TODO - Doesn't display Text
    private void sendPost(String textMessage){
        TextPost newText = new TextPost(textMessage);
        apiService.sendText(newText).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("yes", "Better work already!");
                textArea.setText("");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("no", "Post Failed");
            }
        });
    }
}
