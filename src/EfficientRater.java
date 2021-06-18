
/**
 * Write a description of class EfficientRater here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientRater implements Rater
{
    private String myID;
    private  HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {        
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue(); 
        }else{
            return -1;
        }


        
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String key : myRatings.keySet() ) {
            list.add(key);
        }
        return list;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EfficientRater rater = (EfficientRater) o;
        return Objects.equals(myID, rater.myID) &&
                Objects.equals(myRatings, rater.myRatings);
    }

    public int hashCode() {
        return Objects.hash(myID, myRatings);
    }

    public String toString () {
        StringBuilder rater = new StringBuilder("");
        rater.append("IMBD ID: ");
        rater.append(myID);
        rater.append(" -> ");
        rater.append("{");
        int size = 0;
        for (String id : myRatings.keySet() ) {
            rater.append(myRatings.get(id).toString());
            if (size != myRatings.size()-1) {
                rater.append(",");
            }
            size++;
        }
        rater.append("}");
        return rater.toString();
    }
}
