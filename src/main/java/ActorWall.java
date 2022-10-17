import java.util.ArrayList;
import java.util.List;
public class ActorWall {
    private String name;
    private List<String> movies = new ArrayList<String>();
    private List<String> characters = new ArrayList<String>();

    /**
     * Constructor for ActorWall
     * @param name name of actor
     */
    public ActorWall(String name){
        this.name = name;
    }

    /**
     * Adds movie name and character name using same index in separate arraylists
     * @param movie movie name
     * @param character character name
     */
    public void addMovie(String movie, String character){
        movies.add(movie);
        characters.add(character);
    }

    /**
     * Prints all movies and characters in those movies
     */
    public void getMovies(){
        System.out.println("Actor: " + this.name);
        for (int i = 0; i < this.movies.size(); i++){
            System.out.println("* Movie: " + movies.get(i) + " as " + characters.get(i));
        }
    }

    /**
     *
     * @return actor name
     */
    public String getName(){
        return name;
    }
}
