
/**
 * Write a description of class FourthRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings
{
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> items = MovieDatabase.filterBy(new TrueFilter());
        double product = 0.0;
        
        String meID = me.getID();
        String rID = r.getID();
        Rater meAfter = RaterDatabase.getRater(meID);
        Rater rAfter = RaterDatabase.getRater(rID);

        for (String item: items) {
            if (meAfter.hasRating(item) && rAfter.hasRating(item)) {
                double meScaledScore = meAfter.getRating(item) - 5.0;
                double rScaledScore = rAfter.getRating(item) - 5.0;
                double temp = meScaledScore * rScaledScore;
                product += temp;
            }
        }
        return product;
    }

    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (! r.equals(me)) {
                double product = dotProduct(me,r);
                if (product > 0.0) {
                    list.add(new Rating(r.getID(),product));
                }
            }
            
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;

    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> movieRatings = new ArrayList<Rating>();
        ArrayList<Rating> raterRatings = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String mov : movies) {
             double sum = 0.0;
             int count = 0;
             //filter by numSimilarRaters
             for (int i = 0; i < numSimilarRaters; i++) {
                 Rating currSimRater = raterRatings.get(i);
                 String raterID = currSimRater.getItem();
                 Rater currDataRater = RaterDatabase.getRater(raterID);
                 if(currDataRater.hasRating(mov)) {
                     count++;
                     double score = currDataRater.getRating(mov);
                     double res = currSimRater.getValue() * score;
                     sum += res;
                 }
            }
            //filter by minimalRaters
            if (count >= minimalRaters) {
                double weightedAverage = sum / count;
                Rating rating = new Rating(mov, weightedAverage);
                movieRatings.add(rating);
            }
         }
         Collections.sort(movieRatings);
         Collections.reverse(movieRatings);
         return movieRatings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter f){
        ArrayList<Rating> movieRatings = new ArrayList<Rating>();
        ArrayList<Rating> raterRatings = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(f);
        for (String mov : movies) {
             double sum = 0.0;
             int count = 0;
             //filter by numSimilarRaters
             if (numSimilarRaters > raterRatings.size()) {
                 numSimilarRaters = raterRatings.size();
             }
             for (int i = 0; i < numSimilarRaters; i++) {
                 Rating currSimRater = raterRatings.get(i);
                 String raterID = currSimRater.getItem();
                 Rater currDataRater = RaterDatabase.getRater(raterID);
                 if(currDataRater.hasRating(mov)) {
                     count++;
                     double score = currDataRater.getRating(mov);
                     double res = currSimRater.getValue() * score;
                     sum += res;
                 }
            }
            //filter by minimalRaters
            if (count >= minimalRaters) {
                double weightedAverage = sum / count;
                Rating rating = new Rating(mov, weightedAverage);
                movieRatings.add(rating);
            }
         }
         Collections.sort(movieRatings);
         Collections.reverse(movieRatings);
         return movieRatings;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,AllFilters f){
        ArrayList<Rating> movieRatings = new ArrayList<Rating>();
        ArrayList<Rating> raterRatings = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(f);
        for (String mov : movies) {
             double sum = 0.0;
             int count = 0;
             //filter by numSimilarRaters
             for (int i = 0; i < numSimilarRaters; i++) {
                 Rating currSimRater = raterRatings.get(i);
                 String raterID = currSimRater.getItem();
                 Rater currDataRater = RaterDatabase.getRater(raterID);
                 if(currDataRater.hasRating(mov)) {
                     count++;
                     double score = currDataRater.getRating(mov);
                     double res = currSimRater.getValue() * score;
                     sum += res;
                 }
            }
            //filter by minimalRaters
            if (count >= minimalRaters) {
                double weightedAverage = sum / count;
                Rating rating = new Rating(mov, weightedAverage);
                movieRatings.add(rating);
            }
         }
         Collections.sort(movieRatings);
         Collections.reverse(movieRatings);
         return movieRatings;
    }


    public double getAverageByID(String id, int minimalRaters){
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,AllFilters filterCriteria){
        ArrayList<Rating> avrRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
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

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> avrRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
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
