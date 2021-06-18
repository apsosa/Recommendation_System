import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of class FirstRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FirstRatings
{	
	// private ArrayList<Movie> myMovies;
	// private ArrayList<Rater> myRaters;
	
	// public FirstRatings(String movieFile, String ratingsFile){
	// 	myRaters = loadRaters(ratingsFile);
	// 	myMovies = loadMovies(movieFile);

	// }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ArrayList<Movie> loadMovies(String filename)
    {   
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        try{
    
            for (CSVRecord record : parser.getRecords()) {
    
                String id =record.get("id");
                String title =record.get("title");
                String year = record.get("year");
                String genre = record.get("genre");
                String director = record.get("director");
                String country = record.get("country");
                String poster = record.get("poster");
                int min = Integer.parseInt(record.get("minutes").trim());
                // String minu = record.get(6);
                // int min = Integer.valueOf(minu);
    
                Movie currMovie = new Movie(id,title,year,genre,director,country,poster,min);
                movies.add(currMovie);
            }
        }
        catch (Exception ioe){
            System.out.println("IOExeception caught");
        }

        return movies;
        
    }

    public ArrayList<Movie> getGenre(ArrayList<Movie> movies,String genre){
        ArrayList<Movie> genreMovies = new ArrayList<Movie>();
        for (Movie currMovie : movies ) {
            String currGenre = currMovie.getGenres();
            currGenre = currGenre.toUpperCase();
            if (currGenre.contains(genre.toUpperCase())) {
                genreMovies.add(currMovie);
            }
        }
        return genreMovies;
    }

    public ArrayList<Movie> moviesGreaterThan(ArrayList<Movie> movies,int min){
        ArrayList<Movie> minMovies = new ArrayList<Movie>();
        for (Movie currMovie : movies ) {
            if (currMovie.getMinutes() > min) {
                minMovies.add(currMovie);
            }
        }
        return minMovies;
    }

    private HashMap<String,Integer> getDirector(ArrayList<Movie> movies ){
        HashMap<String,Integer> dirMap = new HashMap<String,Integer>();
        for (Movie currMovie : movies ) {
            String currDir = currMovie.getDirector();
            int index = currDir.indexOf(",");
            if (index == -1) {
                if (!dirMap.containsKey(currDir)) {
                    dirMap.put(currDir,1);
                }else{
                    dirMap.put(currDir, dirMap.get(currDir) + 1);
                }
            }else{
                int star = 0;
                while(index == -1){
                    // juna jose camp, jose jose, jalg jao
                    String director = currDir.substring(star,index);
                    if (!dirMap.containsKey(currDir)) {
                        dirMap.put(currDir,1);
                    }else{
                        dirMap.put(currDir, dirMap.get(currDir) + 1);
                    }
                    star = star + director.length()+1;
                    index = currDir.indexOf(",",star);
                }
                String lasDire = currDir.substring(star,currDir.length());
                if (!dirMap.containsKey(lasDire)) {
                        dirMap.put(lasDire,1);
                }else{
                    dirMap.put(lasDire, dirMap.get(lasDire) + 1);
                }
            }            
        }
        return dirMap;
    }

    public String maxNumberMoviesByAnyDirector(ArrayList<Movie> movies){
        HashMap<String,Integer> directors = getDirector(movies);
        int numbers = 0;
        String director = "";
        for (String dir : directors.keySet()) {
            int currNumber = directors.get(dir);
            if (currNumber > numbers) {
                numbers = currNumber;
                director = dir;
            }
        }

        return director+"has directed "+ numbers+ " movies";
    }

    private int indexRater(ArrayList<Rater> raters, String id){
        for (int k = 0; k < raters.size() ; k++) {
            if (raters.get(k).getID().equals(id)) {
                return k;
            }
        }
        return -1;

    }

    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater>  raters = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        try{
            for (CSVRecord record : parser.getRecords()) {
    
                String id =record.get("rater_id");
                // System.out.println(id);
                String movie_ide =record.get("movie_id");
                // System.out.println(movie_ide);
                double value = Double.parseDouble(record.get("rating"));
                String time = record.get("time");
                // System.out.println(time);
                int currIndex = indexRater(raters,id);
                if (currIndex == -1) {
                    // String rating = record.get("rating");
                    Rater currRater = new EfficientRater(id);
                    currRater.addRating(movie_ide,value);
                    raters.add(currRater);
                }else {
                    raters.get(currIndex).addRating(movie_ide,value);
                }
            }
        }
        catch (Exception ioe){
            System.out.println("IOExeception caught");
        }
        return raters;

    }
    public String findNumberOfRatings(ArrayList<Rater> rater,String id){
    	for(Rater rat : rater) {
     
            if (id.equals(rat.getID())) {
            	System.out.println("Rater len : "+rat.numRatings());
                return rat.toString();
            }
        }
        return "No FOUND";
    }

    public String maxNumberOfRating(ArrayList<Rater> rater){
    	int indexOfMax = 0;
    	int max = 0;
    	for (int k = 0; k < rater.size();k++ ) {
    		int currMax = rater.get(k).numRatings();
    		if (currMax > max) {
    			indexOfMax = k;
    			max = currMax;
    			
    		}
    	}
    	if (rater.size() == 0) {
    		return "NO FOUND";
    	}
    	System.out.println("Number of raters"+rater.get(indexOfMax).numRatings());
    	return rater.get(indexOfMax).toString();
    }

    public int ratersPerMovie(ArrayList<Rater>  raters, String movie){
    	int numRaters = 0;
    	for (Rater r : raters) {
    		for (String currMovie: r.getItemsRated()) {
    			if (currMovie.equals(movie)) {
    				numRaters++;
    			}
    		}
    	}
    	return numRaters;
    }

    public int moviesHaveBeenRated(ArrayList<Rater>  raters){
    	ArrayList<String> movies = new ArrayList<String>();
    	for (Rater r : raters) {
    		for (String currMovie: r.getItemsRated()) {
    			if (!movies.contains(currMovie)) {
    				movies.add(currMovie);
    			}
    		}
    	}
    	return movies.size();
    }

    public void testLoadRaters(){
        String file = "data/ratings.csv";
        ArrayList<Rater>  raters = loadRaters(file);
        System.out.println("Raters len: "+ raters.size());
        System.out.println("There are: " + raters.size() + " raters in the file. They are:\n" );
        int files = 0;
        for(Rater rat : raters) {
            System.out.println(rat);
            files++;
            if (files > 10) {
                break;
            }
        }

        //Rater per id
        String id = "193";
        String rat = findNumberOfRatings(raters,id);
        System.out.println("Rater  per id: "+ rat);

        //max Rater
        String maxRater = maxNumberOfRating(raters);
        System.out.println("Max Rater : "+ maxRater);

        //rater per movie
        String movie = "1798709";
        int ra = ratersPerMovie(raters,movie);
        System.out.println("Number of rater in the movie "+movie+ " is :"+ ra);

        //movies have been rated
        int moviesRted = moviesHaveBeenRated(raters);
        System.out.println("Number of movies have been rated :"+moviesRted);

        
    }

    

    public void testLoadMovies(){
        String file = "data/ratedmoviesfull.csv";
        ArrayList<Movie> movies = loadMovies(file);
        System.out.println("Movies len: "+ movies.size());
        System.out.println("There are: " + movies.size() + " movies in the file. They are:\n" );
        int files = 0;
        for(Movie m : movies) {
            System.out.println(m);
            files++;
            if (files > 10) {
                break;
            }
        }
        
        String genre = "Comedy";
        ArrayList<Movie> moviesGenre = getGenre(movies,genre);
        System.out.println("There are "+ moviesGenre.size()+" Movies with "+ genre+ " genre");

        int min = 150;
        ArrayList<Movie> moviesGThan = moviesGreaterThan(movies,min);
        System.out.println("There are "+ moviesGThan.size()+" Movies greater than "+ min+ " minutes");


        String maxDir = maxNumberMoviesByAnyDirector(movies);
        System.out.println(maxDir);

    }
}
