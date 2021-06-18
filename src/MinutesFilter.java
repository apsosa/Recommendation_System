
/**
 * Write a description of class MinutesFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter
{
    // instance variables - replace the example below with your own
    private int min_;
    private int max_;

    /**
     * Constructor for objects of class MinutesFilter
     */
    public MinutesFilter(int min, int max)
    {
        // initialise instance variables
        min_ = min;
        max_ = max;
    }
    @Override
    public boolean satisfies(String id) {
        int minutes = MovieDatabase.getMinutes(id);
        return  minutes>= min_ && minutes <= max_;
    }

}
