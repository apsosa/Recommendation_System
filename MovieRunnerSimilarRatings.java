
/**
 * Write a description of class MovieRunnerSimilarRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings
{
    public void printAverageRatings()
    {
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv" ;

        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        
        FourthRatings rat = new FourthRatings();
        
        System.out.println("Numbers of raters :"+RaterDatabase.size());
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


    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters fr = new AllFilters();
        // fr.addFilter(new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        fr.addFilter(new YearAfterFilter(1990));
        fr.addFilter(new GenreFilter("Drama"));
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv" ;
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        System.out.println("Numbers of raters :"+RaterDatabase.size());
        System.out.println("Numbers of movies :"+MovieDatabase.size());

        //Get AVR ratings with filter by year
        FourthRatings rat = new FourthRatings();
        int ratings = 8;
        ArrayList<Rating> avr = rat.getAverageRatingsByFilter(ratings,fr);
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

    public void printSimilarRatings(){
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv" ;

        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        
        FourthRatings rat = new FourthRatings();
        
        System.out.println("Numbers of raters :"+RaterDatabase.size());
        System.out.println("Numbers of movies :"+MovieDatabase.size());
        
        //Get AVR ratings
        String id = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;

        ArrayList<Rating> simRat =  rat.getSimilarRatings(id,numSimilarRaters,minimalRaters);
        System.out.println("Numbers of movies have similared rating: "+simRat.size());
        int cantPrint = 0;
        for (Rating r : simRat) {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
    }

    public void printSimilarRatingsByGenre(){
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv" ;

        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        
        FourthRatings rat = new FourthRatings();
        
        System.out.println("Numbers of raters :"+RaterDatabase.size());
        System.out.println("Numbers of movies :"+MovieDatabase.size());
        
        //Get AVR ratings
        String id = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> simRat =  rat.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,f);
        System.out.println("Numbers of movies have similared rating: "+simRat.size());
        int cantPrint = 0;
        for (Rating r : simRat) {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
    }
    
    public void printSimilarRatingsByDirector(){
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv" ;

        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        
        FourthRatings rat = new FourthRatings();
        
        System.out.println("Numbers of raters :"+RaterDatabase.size());
        System.out.println("Numbers of movies :"+MovieDatabase.size());
        
        //Get AVR ratings
        String id = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        String directors ="Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        Filter f = new DirectorsFilter(directors);
        ArrayList<Rating> simRat =  rat.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,f);
        System.out.println("Numbers of movies have similared rating: "+simRat.size());
        int cantPrint = 0;
        for (Rating r : simRat) {
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
    }

    public void printSimilarRatingsByGenreAndMinutes(){
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv" ;

        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        
        FourthRatings rat = new FourthRatings();
        
        System.out.println("Numbers of raters :"+RaterDatabase.size());
        System.out.println("Numbers of movies :"+MovieDatabase.size());
        
        //filters
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new MinutesFilter(80,160));

        //Get AVR ratings
        String id = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        ArrayList<Rating> simRat =  rat.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,f);
        System.out.println("Numbers of movies have similared rating: "+simRat.size());
        int cantPrint = 0;
        for (Rating r : simRat) {
            System.out.println(r.getValue()+" Time "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes(){
        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv" ;

        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        
        FourthRatings rat = new FourthRatings();
        
        System.out.println("Numbers of raters :"+RaterDatabase.size());
        System.out.println("Numbers of movies :"+MovieDatabase.size());
        
        //filters
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1975));
        f.addFilter(new MinutesFilter(70,200));

        //Get AVR ratings
        String id = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        ArrayList<Rating> simRat =  rat.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,f);
        System.out.println("Numbers of movies have similared rating: "+simRat.size());
        int cantPrint = 0;
        for (Rating r : simRat) {
            System.out.println(r.getValue()+" Time "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(r.getItem()));
            cantPrint++;
            if (cantPrint > 11) {
                break;
            }
        }

    }
}
