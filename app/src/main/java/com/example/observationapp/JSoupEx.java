package com.example.observationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**This is the launching activity for our app. This activity contains
 * 1 button and 2 edittexts that will be used to populate the information
 * we scrape. This is mainly for display and educational purposes than
 * any actual use for a mmobile app**/
public class JSoupEx extends AppCompatActivity {

    /**Because I will be using the Textviews in a later activity,
     * I stored them as static variables. I named them quote and author
     * although I ended up only scraping the article title and not the journalist**/
    static TextView quote;
    static TextView author;

    /**This is the method that runs before the layout of the UI has been built.
     * This is the very start of the activity life cycle, as is with all
     * onCreate methods within activities within Android Studios**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup_ex);

        //This line of code is where I find the xml element that is actually related to the Textviews
        //I did this in the onCreate method because I only needed to do this once before the UI was created
        quote = (TextView) findViewById(R.id.quote);
        author = (TextView) findViewById(R.id.author);
    }



    /**Description: This method is the onClick method that runs the web scraper in the background
     * Because the web scraper is an extension of the AsyncTask class, we just run the static
     * method .execute() on a new instance of the class. This method will scrape and post the new contents everytime
     * the button is pressed.
     * @param: View v of the Element being pressed
     * @return: void**/
    public void scrape(View v){
        new RetrieveFeedTask().execute();
    }
}
