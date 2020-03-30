package com.example.observationapp;
/**This is my custom class for the Articles. We are scraping the Fox News for artciles, so
 * I made the Article Class to reflect this**/
public class Article {
    //We will be scraping the title of the article, the video associated with it, at the picture.
    private String title;
    private String video;
    private String picture;

    /**This is the general Article constructor for the Firebase usage.
     * You can scrape directly to Firebase Database, so I made this custom class to reflect
     * this possibility.**/
    public Article(){
        title = "";
        video = "";
        picture = "";
    }

    /**This is the Specific constructor that I will use to populate ArrayLists etc. It takes 3 parameters,
     * the 3 strings nad stores them into the private variable.**/
    public Article(String title, String video, String picture){
        this.title = title;
        this.video = video;
        this.picture = picture;
    }


    /**Description: Th is is the getter method for the title  variable. We can use it,\
     * but I implemented mostly for Firebase as Firebase Database requires getter methods
     * for classes
     * @param: None
     * @return: String title**/
    public String getTitle() { return title; }
    /**Description: Th is is the getter method for the video variable. We can use it,\
     * but I implemented mostly for Firebase as Firebase Database requires getter methods
     * for classes
     * @param: None
     * @return: String video**/
    public String getVideo(){return video; }
    /**Description: Th is is the getter method for the picture variable. We can use it,\
     * but I implemented mostly for Firebase as Firebase Database requires getter methods
     * for classes
     * @param: None
     * @return: String picture**/
    public String getPicture() { return picture; }
}
