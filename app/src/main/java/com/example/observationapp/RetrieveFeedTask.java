package com.example.observationapp;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {

        Document document;
        Element quote;
        Element author;

        @Override
        protected Void doInBackground(Void... voids) {
                String url = "https://kissanime.ru/";
                try {
                        document = Jsoup.connect(url).get();
                        quote = document.selectFirst("span.text");
                        author = document.selectFirst("small.author");
                }
                catch (Exception e){
                        Log.i("error", "nullPointerException");
                }
                return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                JSoupEx.quote.setText(quote.text());
                JSoupEx.author.setText(author.text());
       }
}