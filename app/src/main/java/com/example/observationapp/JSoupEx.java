package com.example.observationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JSoupEx extends AppCompatActivity {

    static TextView quote;
    static TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup_ex);

        quote = (TextView) findViewById(R.id.quote);
        author = (TextView) findViewById(R.id.author);
    }



    public void scrape(View v){
        new RetrieveFeedTask().execute();
    }
}
