
/**
 * Write a description of class MovieRunnerWithFilters here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters
{
    public void printAverageRatings()
    {
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv" ;
        MovieDatabase.initialize(movieFile);
        ThirdRatings rat = new ThirdRatings(ratingFile);
        System.out.println("Numbers of raters :"+rat.getRaterSize());
        System.out.println("Numbers of movies :"+MovieDatabase.size());
        
        //Get AVR ratings
        int ratings = 35;
        ArrayList<Rating> avr =  rat.getAverageRatings(ratings);
        System.out.println("Numbers of movies have "+ratings+" or more ratings is : "+avr.size());
        Collections.sort(avr);
        int cantPrint = 0;
        for (Rating r : avr) {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
        
    }


    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,AllFilters filterCriteria, ThirdRatings rat){
        ArrayList<Rating> avrRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String mov : movies) {
            double avr = rat.getAverageByID(mov,minimalRaters);
            if (avr != 0.0) {
                Rating currRating = new Rating(mov,avr);
                if (!avrRatings.contains(currRating)) {
                    avrRatings.add(currRating);
                }
            }
        }
        
        return avrRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria, ThirdRatings rat){
        ArrayList<Rating> avrRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String mov : movies) {
            double avr = rat.getAverageByID(mov,minimalRaters);
            if (avr != 0.0) {
                Rating currRating = new Rating(mov,avr);
                if (!avrRatings.contains(currRating)) {
                    avrRatings.add(currRating);
                }
            }
        }
        
        return avrRatings;
    }

    public void printAverageRatingsByYear(){
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv" ;
        MovieDatabase.initialize(movieFile);
        ThirdRatings rat = new ThirdRatings(ratingFile);
        System.out.println("Numbers of raters :"+rat.getRaterSize());
        System.out.println("Numbers of movies :"+MovieDatabase.size());

        //Get AVR ratings with filter by year
        int ratings = 20;
        ArrayList<Rating> avr =  getAverageRatingsByFilter(ratings, new YearAfterFilter(2000),rat);
        System.out.println("Numbers of movies have "+ratings+" or more ratings is : "+avr.size());
        Collections.sort(avr);
        int cantPrint = 0;
        for (Rating r : avr) {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
        

    }

    public void printAverageRatingsByGenre(){
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv" ;
        MovieDatabase.initialize(movieFile);
        ThirdRatings rat = new ThirdRatings(ratingFile);
        System.out.println("Numbers of raters :"+rat.getRaterSize());
        System.out.println("Numbers of movies :"+MovieDatabase.size());

        //Get AVR ratings with filter by year
        int ratings = 20;
        ArrayList<Rating> avr =  getAverageRatingsByFilter(ratings, new GenreFilter("Comedy"),rat);
        System.out.println("Numbers of movies have "+ratings+" or more ratings is : "+avr.size());
        Collections.sort(avr);
        int cantPrint = 0;
        for (Rating r : avr) {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
    }

    public void printAverageRatingsByMinutes(){
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv" ;
        MovieDatabase.initialize(movieFile);
        ThirdRatings rat = new ThirdRatings(ratingFile);
        System.out.println("Numbers of raters :"+rat.getRaterSize());
        System.out.println("Numbers of movies :"+MovieDatabase.size());

        //Get AVR ratings with filter by year
        int ratings = 5;
        ArrayList<Rating> avr =  getAverageRatingsByFilter(ratings, new MinutesFilter(105,135),rat);
        System.out.println("Numbers of movies have "+ratings+" or more ratings is : "+avr.size());
        Collections.sort(avr);
        int cantPrint = 0;
        for (Rating r : avr) {
            System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
    }

    public void printAverageRatingsByDirectors(){
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv" ;
        MovieDatabase.initialize(movieFile);
        ThirdRatings rat = new ThirdRatings(ratingFile);
        System.out.println("Numbers of raters :"+rat.getRaterSize());
        System.out.println("Numbers of movies :"+MovieDatabase.size());

        //Get AVR ratings with filter by year
        int ratings = 4;
        ArrayList<Rating> avr =  getAverageRatingsByFilter(ratings, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"),rat);
        System.out.println("Numbers of movies have "+ratings+" or more ratings is : "+avr.size());
        Collections.sort(avr);
        int cantPrint = 0;
        for (Rating r : avr) {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(r.getItem()));           
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters fr = new AllFilters();
        // fr.addFilter(new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        fr.addFilter(new YearAfterFilter(1990));
        fr.addFilter(new GenreFilter("Drama"));
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv" ;
        MovieDatabase.initialize(movieFile);
        ThirdRatings rat = new ThirdRatings(ratingFile);
        System.out.println("Numbers of raters :"+rat.getRaterSize());
        System.out.println("Numbers of movies :"+MovieDatabase.size());

        //Get AVR ratings with filter by year
        int ratings = 8;
        ArrayList<Rating> avr = getAverageRatingsByFilter(ratings,fr,rat);
        System.out.println("Numbers of movies have "+ratings+" or more ratings is : "+avr.size());
        Collections.sort(avr);
        int cantPrint = 0;
        for (Rating r : avr) {
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(r.getItem()));           
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes(){
        AllFilters fr = new AllFilters();
        fr.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        fr.addFilter(new MinutesFilter(90,180));
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv" ;
        MovieDatabase.initialize(movieFile);
        ThirdRatings rat = new ThirdRatings(ratingFile);
        System.out.println("Numbers of raters :"+rat.getRaterSize());
        System.out.println("Numbers of movies :"+MovieDatabase.size());

        //Get AVR ratings with filter by year
        int ratings = 3;
        ArrayList<Rating> avr = getAverageRatingsByFilter(ratings,fr,rat);
        System.out.println("Numbers of movies have "+ratings+" or more ratings is : "+avr.size());
        Collections.sort(avr);
        int cantPrint = 0;
        for (Rating r : avr) {
            System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
    }
}
