import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.io.*;
import java.lang.*;
import java.nio.charset.StandardCharsets;

public class ChatBot {
    private String name;
    private boolean alive;
    private static HashMap<String, Food> holidayFood;
    //private DataBase dataBase;

    static {
        holidayFood = new HashMap<>();
        holidayFood.put("new year", new Food("Olivier salad"));
        holidayFood.put("birthday", new Food("Mashed potato"));
        holidayFood.put("valentine's day", new Food("Spaghetti"));
        holidayFood.put("1st september", new Food("Cake"));
        holidayFood.put("christmas", new Food("Pelmeni"));
        holidayFood.put("thanksgiving day", new Food("Turkey as food"));
        holidayFood.put("maslenitsa", new Food("Pancake"));
        holidayFood.put("1st may", new Food("Pie"));
        holidayFood.put("9th may", new Food("Porridge"));
        holidayFood.put("1st april", new Food("Pie"));
        holidayFood.put("russia day", new Food("Borscht"));
    }

    public HashMap<String, Command> commands = new HashMap<>();
    /*static {
        commands = new HashMap<>();
        commands.put("echo", Command("repeat your text", echo);
        commands.put("getName", getName);
        commands.put("help", help);
        commands.put("holiday", getHolidayFood);
    }*/

    public String echo(String txt) {
        return txt;
    }

    public String getName(String txt) {
        return name;
    }

    public String help(String txt) {
        StringBuilder result = new StringBuilder();
        for (String key : commands.keySet()) {
            result.append(key);
            result.append("- ");
            result.append(commands.get(key).description);
            result.append("\n");
        }
        return result.toString();
    }

    public String getHolidayFood(String arg) { //also we can do Livenstein distance support
    	StringBuilder str = new StringBuilder();
        if (holidayFood.get(arg) == null) {
            str.append("Available variants:\n");
            int counter = 0;
            for (String holiday : holidayFood.keySet()) {
                if (holiday.contains(arg)) {
                    str.append(holiday);
                    str.append("\n");
                    counter += 1;
                }
            }
            str.append("summary " + counter + " variants");
        } else {
            str.append(holidayFood.get(arg).name);
            str.append('\n');
            str.append(getDescription(holidayFood.get(arg)));
        }
        return str.toString();
    }

    public boolean isAlive() {
        return this.alive;
    }

    public String getDescription(Food food){ // if we have description then ok
                                            // if we don't have then find it in wiki
        if (food.description != "")
            return food.description;
        
        StringBuilder description = new StringBuilder();
        String page = findPageWithDescription(food);
        Pattern pattern = Pattern.compile("<p>.*?<b>.+?</p>");
        Matcher matcher = pattern.matcher(page);
        if (!(matcher.find())){
        	return "not found";
        }
        description.append(page.substring(matcher.start() + 6, matcher.end() - 4).replaceAll("<.+?>", ""));
        description.append("\n");
        description.append("you can find recipes here: ");
        description.append("https://recipebook.io/recipes?key=" + food.name);
        //dataBase.add(..)
        
        return description.toString();
    }
    
    private String findPageWithDescription(Food food) {
    	URL url;
    	String page = "";
    	try {
        	url = new URL("https://en.wikipedia.org/wiki/" + food.name.replace(' ', '_'));
        	URLConnection connection = url.openConnection();
            BufferedReader br = new BufferedReader(
            		new InputStreamReader(connection.getInputStream()));
            StringBuilder pageBuffer = new StringBuilder();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
            	pageBuffer.append(inputLine);
            }
            br.close();
            page = pageBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
        	e.printStackTrace();
        }
    	return page;
    }

    ChatBot(String name) {
        this.name = name;
        this.alive = true;
        commands.put("echo", new Command("repeat your text", this::echo));
        commands.put("getName", new Command("get bot name", this::getName));
        commands.put("help", new Command("get information about command", this::help));
        commands.put("holiday", new Command("gives you information about food for holidays", this::getHolidayFood));
    }
}