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

    public String getHolidayFood(String arg) {
        if (holidayFood.get(arg) == null) {
            StringBuilder str = new StringBuilder();
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
        commands.put("echo", new Command("repeat your text", this::echo));
        commands.put("getName", new Command("get bot name", this::getName));
        commands.put("help", new Command("get information about command", this::help));
        commands.put("holiday", new Command("gives you information about food for holidays", this::getHolidayFood));
    }
}