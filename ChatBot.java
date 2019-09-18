import java.util.HashMap;
import java.util.function.Function;

public class ChatBot
{
    private String name;
    public HashMap<String, Function<String, String>> Comands = new HashMap<>();

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
        for (var key : Comands.keySet())
        {
            result += key + " ";
        }
        return result;
    }

    ChatBot(String name)
    {
        this.name = name;
        Comands.put("echo", this::echo);
        Comands.put("getName", this::getName);
        Comands.put("help", this::help);
    }
}
