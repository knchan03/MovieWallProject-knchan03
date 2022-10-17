import com.opencsv.*;

import java.io.BufferedReader;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class project01{

    public static void addActor(String actor, String movie, String character, List<ActorWall> actorsList){

        for (int i = 0;i < actorsList.size(); i++){
            if (actorsList.get(i).getName().equalsIgnoreCase(actor)){
                actorsList.get(i).addMovie(movie, character);
                return;
            }
        }
        ActorWall wall = new ActorWall(actor);
        wall.addMovie(movie, character);
        actorsList.add(wall);
    }

    public static void quickSort(List<ActorWall> unsorted_actors, int left, int right) {
        if (left < right){
            int p = partition(unsorted_actors, left, right);
            quickSort(unsorted_actors, left, p - 1);
            quickSort(unsorted_actors, p + 1, right);
        }
    }
    public static void swap(List<ActorWall> actorWall, int i, int j) {
        ActorWall temp = new ActorWall(" ");
        temp = actorWall.get(i);
        actorWall.set(i, actorWall.get(j));
        actorWall.set(j, temp);
    }
    public static int partition(List<ActorWall> actorWall, int left, int right) {
        int pivot = right;

        int i = (left - 1);

        for (int j = left; j <= right - 1; j++){
            if (actorWall.get(j).getName().compareTo(actorWall.get(pivot).getName()) < 0){
                i++;
                swap(actorWall, i, j);
            }
        }
        swap(actorWall,i + 1, right);
        return (i + 1);
    }
    public static void printAllActors(List<ActorWall> actorWall){
        for (int i = 0; i < actorWall.size(); i++){
            //if (Character.isUpperCase(actorWall.get(i).getName().charAt(0)) == true)
            System.out.println(actorWall.get(i).getName());
        }
        System.out.println(actorWall.size());
    }

    public static int search(List<ActorWall> actorWall, String actor){
        int min = 0;
        int max = actorWall.size() - 1;
        int mid = 0;
        while (min <= max){
            mid = (min + max) / 2;
            if (actorWall.get(mid).getName().compareTo(actor) == 0){
                actorWall.get(mid).getMovies();
                return -1;
            }
            else if (actorWall.get(mid).getName().compareTo(actor) < 0){
                min = mid + 1;
            }
            else{
                max = mid - 1;
            }
        }
        return mid;
    }

    public static List<ActorWall> readFile(String stream){
        List<ActorWall> actorsList = new ArrayList<ActorWall>();
        try{

            File file = new File(stream);
            FileReader file_reader = new FileReader(file);
            BufferedReader buff_reader = new BufferedReader(file_reader);

            String line;
            int count = 0;
            int char_count = 0;
            String actor = "";
            String[] line_data;
            String[] movie_data;
            String[] cast_data;
            String[] character_data;
            String character_name = "";
            String movie_name;
            while ((line = buff_reader.readLine()) != null) {
                if (count != 0) {
                    line_data = line.split("\\[\\{");
                    if (line_data.length > 1) {
                        movie_data = line_data[0].split(",");
                        movie_name = movie_data[1];
                        cast_data = line_data[1].split("}, ");
                        for (int i = 0; i < cast_data.length; i++) {
                            character_data = cast_data[i].split(", ");
                            if (character_data[1].contains("character"))
                                character_name = character_data[1].replace("\"\"character\"\": ", "").replace("\"", "");
                            if ((character_data[5].contains("name")))
                                actor = character_data[5].replace("\"\"name\"\": ", "").replace("\"", "");
                            addActor(actor, movie_name, character_name, actorsList);
                            char_count++;
                        }
                    }
                }
                count++;
            }
            file_reader.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return actorsList;
    }

    public static void main(String[] args) {
        List<ActorWall> actors = readFile("src/main/java/tmdb_5000_credits.csv");
        quickSort(actors, 0, actors.size() - 1);
        Scanner sc = new Scanner(System.in);
        String actor = "";
        printAllActors(actors);
        System.out.println("Welcome to Movie Wall!");
        while (!(actor.equalsIgnoreCase("EXIT"))) {
            System.out.println("Enter the name of an actor (or \"EXIT\" to quit): ");
            actor = sc.nextLine();
            int index = search(actors, actor);
            if (index != -1 && !actor.equals("EXIT")){
                System.out.println("No such actor. Did you mean \" " + actors.get(index).getName() + "\"? (Y/N)");
                String yn = sc.nextLine();
                if (yn.equals("Y") || yn.equals("y")){
                    actors.get(index).getMovies();
                }
            }
        }
        System.out.println("Thanks for using the Movie Wall!");
    }
}