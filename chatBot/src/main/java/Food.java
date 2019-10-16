import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class Food {
    public String name;
    public String description;

    public void setInDB() {
        Gson master = new Gson();
        String jsonFood = master.toJson(this) + "\n";
        try {
            Files.write(Paths.get("C:\\Users\\1268547\\IdeaProjects\\chatBot\\src\\main\\resources\\DB.txt"),
                    jsonFood.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    Food(String name) {
        this.description = "";
        Gson master = new Gson();
        this.name = name;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "C:\\Users\\1268547\\IdeaProjects\\chatBot\\src\\main\\resources\\DB.txt"));
            String jsonFood;
            while ((jsonFood = reader.readLine()) != null) {
                Food food = master.fromJson(jsonFood, this.getClass());
                if (food.name.equals(name)) {
                    description = food.description;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public ArrayList<string> ingridients;
}