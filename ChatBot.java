import java.util.HashMap;
import java.util.function.Function;

public class ChatBot
{
    private String name;
    private boolean alive;
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

    public String rap(String txt)
    {
        //generate_text();
        return "To be, or not to be: that is the question:\n" +
                "Whether 'tis nobler in the mind to suffer\n" +
                "The slings and arrows of outrageous fortune,\n" +
                "Or to take arms against a sea of troubles,\n" +
                "And by opposing end them? To die: to sleep;\n" +
                "No more; and by a sleep to say we end\n" +
                "The heart-ache and the thousand natural shocks\n" +
                "That flesh is heir to, 'tis a consummation\n" +
                "Devoutly to be wish'd. To die, to sleep;\n" +
                "To sleep: perchance to dream: ay, there's the rub;\n" +
                "For in that sleep of death what dreams may come\n" +
                "When we have shuffled off this mortal coil,\n" +
                "Must give us pause: there's the respect\n" +
                "That makes calamity of so long life;";
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
        Commands.put("rap", this::rap);

    }
}
