import java.util.HashMap;
import java.util.function.Function;

public class ChatBot
{
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
    public HashMap<String, Function<String, String>> Commands = new HashMap<>();

    public String echo(String txt)
    {
        return txt;
    }

    public String getName(String txt)
    {
        return name;
    }

    public String help(String txt)
    {
        String result = "";
        for (var key : Commands.keySet())
        {
            result += key + " ";
        }
        return result;
    }

    public String bye(String txt)
    {
        this.alive = false;
        return "bye";
    }

    public String getHolidayFood(String arg)
    {
        return holidayFood.get(arg);
    }

    public boolean isAlive()
    {
        return this.alive;
    }

    ChatBot(String name)
    {
        this.name = name;
        this.alive = true;
        System.out.print("Yo I'm " + name + "\nAt your service...\n");
        Commands.put("echo", this::echo);
        Commands.put("getName", this::getName);
        Commands.put("help", this::help);
        Commands.put("bye", this::bye);
        Commands.put("holiday", this::getHolidayFood);

    }
}
