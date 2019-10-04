import java.util.HashMap;
import java.lang.*;

public class ChatBot {
    private String name;
    private boolean alive;
    private static HashMap<String, String> holidayFood;

    static {
        holidayFood = new HashMap<>();
        holidayFood.put("new year", "olivye it's all of you need");
        holidayFood.put("birthday", "mashed potatoes with chicken and compote");
        holidayFood.put("valentine's day", "spaghetti");
        holidayFood.put("1st september", "cake");
        holidayFood.put("christmas", "turkey");
        holidayFood.put("thanksgiving day", "turkey");
        holidayFood.put("maslenitsa", "pancakes");
        holidayFood.put("1st may", "pie");
        holidayFood.put("9st may", "buckwheat with meat");
        holidayFood.put("1st april", "explosive pie");
        holidayFood.put("russia day", "borsch");
    }

    public HashMap<String, Command> Commands = new HashMap<>();
    /*static {
        Commands = new HashMap<>();
        Commands.put("echo", Command("repeat your text", echo);
        Commands.put("getName", getName);
        Commands.put("help", help);
        Commands.put("holiday", getHolidayFood);
    }*/

    public String echo(String txt) {
        return txt + "\n";
    }

    public String getName(String txt) {
        return name + "\n";
    }

    public String help(String txt) {
        StringBuilder result = new StringBuilder();
        for (String key : Commands.keySet()) {
            result.append(key);
            result.append("- ");
            result.append(Commands.get(key).description);
            result.append("\n");
        }
        return result.toString();
    }

    public String getHolidayFood(String arg) {
        if (holidayFood.get(arg) == null) {
            StringBuilder str = new StringBuilder();
            str.append("Available variants:\n");
            for (String holiday : holidayFood.keySet()) {
                if (holiday.contains(arg)) {
                    str.append(holiday);
                    str.append("\n");
                }
            }
            return str.toString();
        } else {
            return holidayFood.get(arg);
        }
    }

    public boolean isAlive() {
        return this.alive;
    }

    ChatBot(String name) {
        this.name = name;
        this.alive = true;
        System.out.print("Yo I'm " + name + "\nAt your service...\n");
        Commands.put("echo", new Command("repeat your text", this::echo));
        Commands.put("getName", new Command("get bot name", this::getName));
        Commands.put("help", new Command("get information about command", this::help));
        Commands.put("holiday", new Command("gives you information about food for holidays", this::getHolidayFood));
    }
}