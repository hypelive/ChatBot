import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.*;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.*;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;


public class TelegramBot extends TelegramLongPollingBot{
	ChatBot bot;

	public static void main(String[] args) {
		ApiContextInitializer.init();
		bot = new ChatBot("Alex");
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(TelegramBot.getBot());
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return "OptimalCookBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		String message = update.getMessage().getText();
		String name = message.split(" ")[0];
		String arg = "";
		if (message.length() >= 2)
			arg = message.substring(message.indexOf(" ") + 1);
		String result;
		if (bot.commands.containsKey(name)) {
			result = bot.commands.get(name).func.apply(arg.toLowerCase());
		}
		sendMsg(update.getMessage().getChatId().toString(), result);
	}

	public void sendMsg(String chatId, String s) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(chatId);
		sendMessage.setText(s);
		try {
			sendMessage(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotToken() {
		return "1068011791:AAGXnnhwJ1xDkWwRhzcQEYzw2y-ftuzZXts";
	}
}