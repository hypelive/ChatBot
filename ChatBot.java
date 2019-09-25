import java.util.HashMap;
import java.util.function.Function;

public class ChatBot
{
    private String name;
    private boolean alive;
    private static HashMap<String, String> holydayFood;
    static {
        holydayFood = new HashMap<>();
        holydayFood.put("new year", "olivye it's all of you need");
        holydayFood.put("birthday", "mashed potatoes with chicken and compote");
        holydayFood.put("valentine's day", "spaghetti");
        holydayFood.put("1st september", "cake");
        holydayFood.put("christmas", "turkey");
        holydayFood.put("thanksgiving day", "turkey");
        holydayFood.put("maslenitsa", "pancakes");
        holydayFood.put("1st may", "pie");
        holydayFood.put("9st may", "buckwheat with meat");
        holydayFood.put("1st april", "explosive pie");
        holydayFood.put("russia day", "borch");
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
        return holydayFood.get(arg);
    }

    public boolean is_alive()
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
