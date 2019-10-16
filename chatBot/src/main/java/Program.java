import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        ChatBot bot = new ChatBot("Pavel");
        while (bot.isAlive()) {
            Scanner scan = new Scanner(System.in);
            String inp = scan.nextLine();
            String name = inp.split(" ")[0];
            String arg = "";
            if (inp.length() >= 2)
                arg = inp.substring(inp.indexOf(" ") + 1);
            String result;
            if (bot.commands.containsKey(name)) {
                result = bot.commands.get(name).func.apply(arg.toLowerCase());
            }
            else {
                result = "This command is undefined";
            }
            System.out.println(result);
        }
    }
}