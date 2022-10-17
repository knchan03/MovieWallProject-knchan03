/**
 * Project 01 - MovieWall
 * @author Kevin Chan
 * 10/18/2022
 */

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

public class project01{
    /**
     * Creates ActorWall object and adds it to list of ActorWalls
     * @param actor actor name
     * @param movie movie name
     * @param character character name
     * @param actorsList List of ActorWalls
     */
    public static void addActor(String actor, String movie, String character, List<ActorWall> actorsList){

        for (int i = 0;i < actorsList.size(); i++){
            if (actorsList.get(i).getName().equalsIgnoreCase(actor)){
                actorsList.get(i).addMovie(movie, character);
                return;
            }
        }
        ActorWall wall = new ActorWall(actor); //creates new ActorWall object
        wall.addMovie(movie, character); //adds movie and character played
        actorsList.add(wall); //adds wall to list of ActorWalls
    }

    /**
     * Quicksorts unsorted list of ActorWalls
     * @param unsorted_actors unsorted list of ActorWalls
     * @param left left index
     * @param right right index
     */
    public static void quickSort(List<ActorWall> unsorted_actors, int left, int right) {
        if (left < right){
            int p = partition(unsorted_actors, left, right);
            quickSort(unsorted_actors, left, p - 1);
            quickSort(unsorted_actors, p + 1, right);
        }
    }

    /**
     *
     * @param actorWall List of ActorWalls
     * @param i index of first element being switched
     * @param j index of second element being switched
     */
    public static void swap(List<ActorWall> actorWall, int i, int j) {
        ActorWall temp = new ActorWall(" ");
        temp = actorWall.get(i);
        actorWall.set(i, actorWall.get(j));
        actorWall.set(j, temp);
    }

    /**
     * Partition method of quicksort
     * @param actorWall List of ActorWalls
     * @param left left boundary
     * @param right right boundary
     * @return pivot index
     */
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

    /**
     * Binary searches list of ActorWalls using actor name
     * @param actorWall List of ActorWalls
     * @param actor Actor being searched for
     * @return index of nearest actor if not found, -1 if found
     */
    public static int search(List<ActorWall> actorWall, String actor){
        int min = 0;
        int max = actorWall.size() - 1;
        int mid = 0;
        while (min <= max){
            mid = (min + max) / 2;
            if (actorWall.get(mid).getName().compareTo(actor) == 0){
                actorWall.get(mid).getMovies(); //prints all roles of actor
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

    /**
     * Reads file and adds actors into a list of ActorWalls
     * @param stream filepath
     * @return List of ActorWalls
     */
    public static List<ActorWall> readFile(String stream){
        List<ActorWall> actorsList = new ArrayList<ActorWall>();
        try{

            File file = new File(stream);
            FileReader file_reader = new FileReader(file);
            BufferedReader buff_reader = new BufferedReader(file_reader);

            String line;
            String actor = "";
            String[] line_data;
            String[] movie_data;
            String[] cast_data;
            String[] character_data;
            String character_name = "";
            String movie_name;
            while ((line = buff_reader.readLine()) != null) {
                line_data = line.split("\\[\\{"); //separates json from rest of line
                if (line_data.length > 1) { // checks for header line
                    movie_data = line_data[0].split(","); // splits movie name and movie id
                    movie_name = movie_data[1];
                    cast_data = line_data[1].split("}, "); //splits json text to get all cast members
                    for (int i = 0; i < cast_data.length; i++) {
                        character_data = cast_data[i].split(", "); //splits each cast member data to extract character name and actor name
                        if (character_data[1].contains("character"))
                            character_name = character_data[1].replace("\"\"character\"\": ", "").replace("\"", ""); //gets character name by itself
                        if ((character_data[5].contains("name")))
                            actor = character_data[5].replace("\"\"name\"\": ", "").replace("\"", ""); // gets actor name by itself
                        addActor(actor, movie_name, character_name, actorsList); //adds actor into actorsList
                    }
                }
            }
            file_reader.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return actorsList;
    }

    public static void main(String[] args) {
        List<ActorWall> actors = readFile(args[0]); //path to file stated in configurations (Ex: "src/main/java/tmdb_5000_credits.csv")
        quickSort(actors, 0, actors.size() - 1);
        Scanner sc = new Scanner(System.in);
        String actor = "";
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