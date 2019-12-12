package com.example.observationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DisplayActivity extends AppCompatActivity {

    public DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


     public void onRetrieve(View v){
        mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            User user = dataSnapshot.child("user").getValue(User.class);


            String username = dataSnapshot.child("user").child("username").getValue(String.class);

            TextView nameDisplay = (TextView) findViewById(R.id.nameDisplay);
            TextView passwordDisplay = (TextView) findViewById(R.id.passwordDisplay);

            nameDisplay.setText(user.getUsername());
            passwordDisplay.setText(user.getPassword());
    }

        @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    Log.i("callError", "Brother there has been an Error");
    }
    });
     }

    public void next(View v){
        Intent intent = new Intent(DisplayActivity.this, JSoupEx.class);
        startActivity(intent);
    }
}
