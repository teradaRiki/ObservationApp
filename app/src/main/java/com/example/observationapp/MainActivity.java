package com.example.observationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void onClick(View v){
        EditText username = (EditText) findViewById(R.id.name);
        EditText password = (EditText) findViewById(R.id.password);

        User newUser = new User(username.getText().toString(), password.getText().toString());
        mDatabase.child("user").setValue(newUser);

        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
        startActivity(intent);
    }
}