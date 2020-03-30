package com.example.observationapp;

//First, in order to run the web scraper in the background, we need
//to import the class AsyncTask as such. More explanation later
import android.os.AsyncTask;
//This is to log our progress because errors will most likely result
import android.util.Log;

//This is the line that imports the JSoup library to our Class
import org.jsoup.Jsoup;
//We import a specific class called Documents that we need in order to
//read the website HTML from the link we pulled
import org.jsoup.nodes.Document;
//This is the Element class. This is what we will base most of our scraping from
import org.jsoup.nodes.Element;
//This is a standard ArrayList I am importing to store my Articles in
import java.util.ArrayList;

/**The AsyncTask we are extending from essentially enables us to work in the background thread
 * Because scraping can be a long process that may take time, we cannot block
 * the UI thread as that will ruin the user experience, Java is a multithread coding language,
 * so that means we can use the background thread to process data while the User can still
 * interact with the actual UI thread**/
public class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {

    /**This is the document. The Document Class extends the Element Class
     * but is made mroe directly for the pulling of the website as a whole.
     * There are other small differences you can explore on your own **/
    Document document;
    //This is a declaration of an ArrayList of Articles because I decided to store the info like this
    ArrayList<Article> articles = new ArrayList<>();

    /**In this space, we can use also override a method
     * protected void onPreExecute(Void aVoid)
     * This method also runs in the UI Thread but right before entering the background thread. You can
     * take the inputs of users in this UI thread in order to tailor the web scraping to necessary**/

    /**This is the doInBackground method. Essentially, any code written in this method will be done
     * in a background thread and not the UI thread.  This is an override method, so there are no
     * worries of actually implementing and using this method anywhere in our code. The execute()
     * static method will take care of everything for us**/
    @Override
    protected Void doInBackground(Void... voids) {
        //This is the website link we are scraping from
        //I made it into a variable so that it's easily switchable
        String url = "https://www.foxnews.com/";
        //JSoup require handling Java.ioExceptions, so that's why I use a try catch block
        try {
            // This line connects the URL and gets the website
            //validateTLSCertificates(false) just lets us scrape from secure websites i.e. https
            document = Jsoup.connect(url).validateTLSCertificates(false).get();
            /**This is the for each loop that will run through a list of articles found on the website
             * I found this by taking the document and selecting the first class with the values "content"
             * and "article-list." This is the parent Element of all articles, so I use the .children() to get the iterable**/
            for (Element article: document.selectFirst(".content.article-list").children()) {
                /**To find the video, I went into the Article, found the first child, went into its first direct child,
                 * and found the value of one of its attribute called 'href.' The abs just makes the url link from
                 * a relative path to an absolute path for easier use**/
                String video = article.child(0).child(0).attr("abs:href");
                /**Instead of counting the index and postition to the path of the direct child, this time I directly found
                 * the first Element with the class of value title. Then I  found its text
                 * The text() method gets all the text of that Element and its children. This Element of class title happened
                 * to only have the title of the article as its only text, but this may not necessarily be true so be careful**/
                String title = article.selectFirst(".title").text();
                /**This time, I took a different path to find the image. Instead of selecting the first Element that is an
                 * image element, i just found a list of Elements that are img, and got the first one  using first(). For
                 * this specific project, there is no real difference, but I just did this to show other methods. Then I found
                 * the src by using the same method above**/
                String picture = article.select("img").first().attr("abs:src");

                //I declare and instantiate and Article called story and add it to the ArrayListof articles
                Article story = new Article(title, video, picture);
                articles.add(story);
            }
            //We can implement the Firebase Database in this project, and this is where I would put that code. I didn't for this example.
        }
        //I am catching all exceptions and not just IOExceptions because there are also nullPointerException
        catch (Exception e){
                Log.i("error", "nullPointerException");
        }
        return null;
    }

    /**This is the postExecute method .This method works in the UI thread and not in the background thread, so we can
     * keep the same global variables but still work in the UI thread. This is where we get to make direct changes to the UI
     * using what we did in the background**/
    @Override
    protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //I just logged the document to see that the connection was working
            Log.e("error", document.toString());
            //This is the line where I set the quote TextView to the title of the first article in the list.
            JSoupEx.quote.setText(articles.get(0).getTitle());
   }
}