import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class ActorWall {
    String name;
    HashMap<String, String> movie = new HashMap<String, String>();
    List<HashMap> movies = new ArrayList<HashMap>();

    public ActorWall(String name){
        this.name = name;
    }

    public void addMovie(String movie, String character){
        this.movie.put(movie, character);
        movies.add(this.movie);
    }

    public List<HashMap> getMovies(){
        return movies;
    }

    public String getName(){
        return name;
    }
}
