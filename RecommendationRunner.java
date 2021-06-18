
/**
 * Write a description of class RecommendationRunner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

 

import java.util.*;
public class RecommendationRunner implements Recommender
{
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        if (movies.size() < 21) {
            return movies;
        }else{
            return new ArrayList<String>(movies.subList(0,20));
        }
    }

    private void printMovies(ArrayList<Rating> movies){
        if (movies.size() == 0) {
            System.out.println("Theres are no movies to Recommended");
        }else{
             int cantPrint = 0;
             String style = "<style> </style>";
             String header = ("<table style = \"background-color:powderblue;\"> <tr style = \"font-size:300%;\"> <th>Movie Title</th> " + "<th>Rating</th> <th>Genres</th> </tr> ");
            String body = "";
            for (Rating rating : movies) {
                body += "<tr style = \"font-size:150%;> \"> <th>" + MovieDatabase.getTitle(rating.getItem())
                        + "</th> <th>" + Double.toString(rating.getValue())
                        + "</th> <th>" + MovieDatabase.getGenres(rating.getItem())
                        + "</th></tr>";
                cantPrint++;
                if (cantPrint > 11) {
                    break;
                }
            }
            System.out.println(header + body+ "</table>");
        }

    }

    public void printRecommendationsFor(String webRaterID){

        String movieFile = "ratedmoviesfull.csv";
        String ratingFile = "ratings.csv" ;

        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);

        /*
        System.out.println("<p>Read data for " + 
               Integer.toString(RaterDatabase.size()) + " raters</p>");
        System.out.println("<p>Read data for " + 
               Integer.toString(MovieDatabase.size()) + " movies</p>");
        */
        
        
        FourthRatings rater = new FourthRatings();
        int numSimilarRaters = 10;
        int minimalRaters = 1;
        String directors ="Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        Filter f = new DirectorsFilter(directors);
        ArrayList<Rating> simRat =  rater.getSimilarRatingsByFilter(webRaterID,numSimilarRaters,minimalRaters,f);
        printMovies(simRat);

        /*
        ArrayList<String> test = getItemsToRate();
        System.out.println("Test getItemsToRate :"+test.size());
        for (String mov : test ) {
            System.out.println("Test movies :"+mov);
            
        }
        */
    }
}
