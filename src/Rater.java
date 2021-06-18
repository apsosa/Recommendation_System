
/**
 * Write a description of interface Rater here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public interface Rater
{
	public void addRating(String item, double rating);
	public boolean hasRating(String item);
	public String getID();
	public double getRating(String item);
	public int numRatings();
	public ArrayList<String> getItemsRated();
    public boolean equals(Object o);
	public int hashCode();
	public String toString ();
}	
