
/**
 * Write a description of class DirectorsFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter
{
    // instance variables - replace the example below with your own
    private String directors;

    /**
     * Constructor for objects of class DirectorsFilter
     */
    public DirectorsFilter(String dir)
    {
        directors = dir;
    }
    @Override
    public boolean satisfies(String id) {
        String currDir  = MovieDatabase.getDirector(id);
        int index = currDir.indexOf(",");
            if (index == -1) {
                return directors.contains(currDir);
            }else{
                int star = 0;
                while(index == -1){
                    // juna jose camp, jose jose, jalg jao
                    String director = currDir.substring(star,index);
                    if (directors.contains(currDir)) return true;
                    star = star + director.length()+1;
                    index = currDir.indexOf(",",star);
                }
                String lasDire = currDir.substring(star,currDir.length());
                return directors.contains(lasDire);
            }         
        
    }
}
