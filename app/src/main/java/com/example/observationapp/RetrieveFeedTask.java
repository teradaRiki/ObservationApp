package com.example.observationapp;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {

        Document document;
        ArrayList<Article> articles = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
                String url = "https://www.foxnews.com/";
                try {
                    document = Jsoup.connect(url).validateTLSCertificates(false).get();
                    for (Element article: document.selectFirst(".content.article-list").children()) {
                        String video = article.child(0).child(0).attr("abs:href");
                        String title = article.selectFirst(".title").text();
                        String picture = article.select("img").first().attr("abs:src");

                        Article story = new Article(title, video, picture);
                        articles.add(story);
                    }
                }
                catch (Exception e){
                        Log.i("error", "nullPointerException");
                }
                return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Log.e("error", document.toString());
                JSoupEx.quote.setText(articles.get(0).getTitle());
       }
}