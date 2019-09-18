import java.util.Scanner;
import java.util.function.Function;

public class Program
{
    public static void main(String[] args)
    {
        var bot = new ChatBot("Pavel");
        while (bot.is_alive())
        {
            Scanner scan = new Scanner(System.in);
            String name = scan.next();
            String arg = "";
            if (scan.nextLine().split(" ").length >= 2)
                arg = scan.nextLine().split(" ")[1];      //видимо, тут не то, что задумывалось
            String result;
            if (bot.Commands.containsKey(name))
                result = bot.Commands.get(name).apply(arg);
            else
                result = "This command is undefined";
            System.out.print(result + "\n");
            //Thread.sleep(30); norm tema but douesn't works
        }
    }
}
