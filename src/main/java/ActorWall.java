import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class ActorWall {
    private String name;
    private List<String> movies = new ArrayList<String>();
    private List<String> characters = new ArrayList<String>();

    public ActorWall(String name){
        this.name = name;
    }

    public void addMovie(String movie, String character){
        movies.add(movie);
        characters.add(character);
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void getMovies(){
        System.out.println("Actor: " + this.name);
        for (int i = 0; i < this.movies.size(); i++){
            System.out.println("* Movie: " + movies.get(i) + " as " + characters.get(i));
        }
    }

    public String getName(){
        return name;
    }
}
