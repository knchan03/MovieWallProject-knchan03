import com.opencsv.*;


import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class project01{


    public static String[] removeBraces(String[] strings){
        for (String s : strings) {
            s.replace("[", "");
            s.replace("]", "");
            s.replace("{", "");
            s.replace("}", "");
        }
        return strings;
    }
    public static List<ActorWall> addActor(String actor, String movie, String character, List<ActorWall> actorsList){
        for (int i = 0;i < actorsList.size(); i++){
            if (actorsList.get(i).getName().equalsIgnoreCase(actor)){
                actorsList.get(i).addMovie(movie, character);
                return actorsList;
            }
        }
        ActorWall wall = new ActorWall(actor);
        wall.addMovie(movie, character);
        actorsList.add(wall);
        return actorsList;
    }

    public static void readFile(String stream){
        List<ActorWall> actorsList = new ArrayList<ActorWall>();
        HashMap<String, ArrayList<String>> movies = new HashMap<String, ArrayList<String>>();
        try{
            System.out.println("Welcome to Movie Wall!\nEnter the name of an actor (or \"EXIT\" to quit): ");
            Scanner sc = new Scanner(System.in);
            String actor = sc.next();
            System.out.println(actor + "\n");
            File file = new File(stream);
            FileReader file_reader = new FileReader(file);
            CSVReader csv_reader = new CSVReader(file_reader);
            String [] data;
            int count;
            int i = 0;
            String movie_name = new String();
            boolean skipFirstLine = false;
            while ((data = csv_reader.readNext()) != null){
                count = 0;
                String[] character_info = new String[50];
                String name;
                String character_name;
                for (String cell : data){
                    if (!skipFirstLine){
                        skipFirstLine = true;
                        break;
                    }
                    if (count == 1){
                        movie_name = cell;
                    }
                    if (count == 2){
                        String json_data = cell;
                        String[] cast_info = json_data.split("}, ");
                        cast_info = removeBraces(cast_info);
                        for (String character : cast_info) {
                            character_info = character.split(", ");
                            name = character_info[5].replace("\"name\": ", "").replace("\"", "");
                            character_name = character_info[1].replace("\"character\": ", "").replace("\"", "");
                            int size = actorsList.size();
                            actorsList = addActor(name, movie_name, character_name, actorsList);
                            if (size != actorsList.size()) {
                                System.out.println(actorsList.get(actorsList.size() - 1).getName());
                                System.out.println(actorsList.size());
                            }
                        }
                    }
                    count++;
                }

            }
            csv_reader.close();


        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        readFile("src/main/java/tmdb_5000_credits.csv");
    }
}