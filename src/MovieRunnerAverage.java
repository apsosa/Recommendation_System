
/**
 * Write a description of class MovieRunnerAverage here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage
{
    // instance variables - replace the example below with your own
   

    public void printAverageRatings()
    {
        String movieFile = "data/ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv" ;
        SecondRatings rat = new SecondRatings(movieFile,ratingFile);
        System.out.println("Numbers of movies :"+rat.getMovieSize());
        System.out.println("Numbers of raters :"+rat.getRaterSize());
        
        //Get AVR ratings
        int ratings = 12;
        ArrayList<Rating> avr =  rat.getAverageRatings(ratings);
        System.out.println("Numbers of movies have "+ratings+" or more ratings is : "+avr.size());
        Collections.sort(avr);
        int cantPrint = 0;
        for (Rating r : avr) {
            System.out.println(r.getValue()+" "+rat.getTitle(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }


    }


    public void getAverageRatingOneMovie(){
        SecondRatings rat = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        System.out.println("Numbers of movies :"+rat.getMovieSize());
        System.out.println("Numbers of raters :"+rat.getRaterSize());
        
        String movieTitle = "Vacation";
        if (!rat.getID(movieTitle).equals("NO SUCH TITLE")) {
            double avr = rat.getAverageByID(rat.getID(movieTitle),2);
            System.out.println("Average for the movie "+movieTitle+" is: "+avr);
        }else{
            System.out.println("NO SUCH TITLE");            
        }

    }
}
