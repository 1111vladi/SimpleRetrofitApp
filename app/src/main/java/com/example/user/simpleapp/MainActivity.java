package com.example.user.simpleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import data.model.Post;
import data.remote.APIService;
import data.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mResponse;
    private APIService mAPIService;

    private static final String TAG = "Something";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        mResponse = (TextView)findViewById(R.id.response);
        Button sendBtn = (Button) findViewById(R.id.send);

        mAPIService = ApiUtils.getAPIService();


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if(!TextUtils.isEmpty(user)
                        && !TextUtils.isEmpty(pass)
                        )
                {
                        sendPost(user,pass);
                }
            }
        });
    }


    private void sendPost(String user, String pass) {
        mAPIService.savePost(user, pass, "password").enqueue(new Callback<Post>() {
            @Override

            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.code() == 200 && response.body() != null) {
                    showResponse(response.body().toString());
                    Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(), "Moving to next screen", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e(TAG, "Error Code ***");
            }
        });
    }

    // (Optional) a Testing method to display the data on screen (toString method is missing)
    public void showResponse(String response) {
        if(mResponse.getVisibility() == View.GONE) {
            mResponse.setVisibility(View.VISIBLE);
        }
        mResponse.setText(response);
    }

}
