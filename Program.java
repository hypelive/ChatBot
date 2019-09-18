import java.util.Scanner;
import java.util.function.Function;

public class Program
{
    public static void main(String[] args)
    {
        var bot = new ChatBot("Pavel");
        while (true)
        {
            Scanner scan = new Scanner(System.in);
            String name = scan.next();
            String arg = "";
            if (scan.nextLine().split(" ").length >= 2)
                arg = scan.nextLine().split(" ")[1];
            String result;
            if (bot.Comands.containsKey(name))
                result = bot.Comands.get(name).apply(arg);
            else
                result = "This command is undefined";
            System.out.print(result + "\n");
        }
    }
}
