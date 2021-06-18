
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingsFile) {
        // default constructor
        FirstRatings ratings = new FirstRatings();
        myRaters = ratings.loadRaters(ratingsFile);
		myMovies = ratings.loadMovies(movieFile);
    }

    public int  getMovieSize(){
    	return myMovies.size();
    }

    public int getRaterSize(){
    	return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters){
        int numbersOfRating = 0;
        double sumOfRating = 0;
        for (Rater r : myRaters) {
            double currRating = r.getRating(id);
            if (currRating != -1) {
                numbersOfRating++;
                sumOfRating += currRating;
            }
        }
        if (numbersOfRating < minimalRaters) {
            return 0.0;
        }
        return sumOfRating/numbersOfRating;
    }


    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avrRatings = new ArrayList<Rating>();
        for (Rater r : myRaters ) {
            ArrayList<String> movies = r.getItemsRated();
            for (String mov : movies) {
            double avr = getAverageByID(mov,minimalRaters);
            if (avr != 0.0) {
                Rating currRating = new Rating(mov,avr);
                if (!avrRatings.contains(currRating)) {
                    avrRatings.add(currRating);
                }

            }
        }
            
        }
        
        return avrRatings;
    }

    public String getTitle(String id){
        for (Movie mov : myMovies) {
            if (id.equals(mov.getID())) {
                return mov.getTitle();
            }
        }
        return "NO FOUND";
    }

    public String getID(String title){
        for (Movie mov : myMovies) {
            if (title.equals(mov.getTitle())) {
                return mov.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
}
