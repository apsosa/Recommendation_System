
/**
 * Write a description of class ThirdRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings
{
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsFile) {
        // default constructor
        FirstRatings ratings = new FirstRatings();
        myRaters = ratings.loadRaters(ratingsFile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String mov : movies) {
            double avr = getAverageByID(mov,minimalRaters);
            if (avr != 0.0) {
                Rating currRating = new Rating(mov,avr);
                if (!avrRatings.contains(currRating)) {
                    avrRatings.add(currRating);
                }
            }
            
        }
        return avrRatings;
    }
}
