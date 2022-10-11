import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class project01{


    public static void main(String[] args) {
        try{
            System.out.println("Welcome to Movie Wall!\nEnter the name of an actor (or \"EXIT\" to quit): ");
            Scanner sc = new Scanner(System.in);
            String actor = sc.next();
            System.out.println(actor + "\n");
            File file = new File("src/main/java/tmdb_5000_credits.csv");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] split = data.split(",");
                System.out.println(split[2]);
            }
            reader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Couldn't Open File");
        }
    }
}