import java.util.HashMap;
import java.util.function.Function;
import java.lang.*;

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
    //можно в статик, когда класс комманды будет:)
    /*static {
        Commands = new HashMap<>();
        Commands.put("echo", echo);
        Commands.put("getName", getName);
        Commands.put("help", help);
        Commands.put("holiday", getHolidayFood);
    }*/

    public String echo(String txt)
    {
        return txt + "\n";
    }

    public String getName(String txt)
    {
        return name + "\n";
    }

    public String help(String txt)
    {
        var result = new StringBuilder();
        for (var key : Commands.keySet())
        {
            result.append(key);
            result.append("\n");
        }
        return result.toString();
    }

    public String getHolidayFood(String arg)
    {
        if (holidayFood.get(arg) == null)
        {
            var str = new StringBuilder();
            str.append("Available variants:\n");
            for (var holiday : holidayFood.keySet()) {
                if (holiday.indexOf(arg) != -1) {
                    str.append(holiday);
                    str.append("\n");
                }
            }
            return str.toString();
        }
        else {
            return holidayFood.get(arg);
        }
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
        Commands.put("holiday", this::getHolidayFood);
    }
}
