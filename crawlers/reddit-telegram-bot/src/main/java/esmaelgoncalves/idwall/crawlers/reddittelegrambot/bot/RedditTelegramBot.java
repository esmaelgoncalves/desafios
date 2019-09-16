package esmaelgoncalves.idwall.crawlers.reddittelegrambot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RedditTelegramBot extends TelegramLongPollingBot {

    public static final String COMMAND_NADA_PRA_FAZER = "/nadaprafazer";
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        log.trace("Receiving a update event: {}", update);
        if (update.hasMessage() && update.getMessage().hasText()) {

            // Set variables
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            log.debug("Received message: {} from ChatID: {}", messageText, chatId);

            SendMessage message = new SendMessage();
            if (messageText.startsWith(COMMAND_NADA_PRA_FAZER)) {
                log.debug("Known command: {}", COMMAND_NADA_PRA_FAZER);

                List<String> subreddits = getSubRedditsFromCommand(messageText);

                message.setChatId(chatId);
                message.setText("Comando executado : '/nadaprafazer''");
            } else {
                log.info("Unknown command executed.");
                message.setChatId(chatId);
                message.setText("Comando não reconhecido.");
            }
            // Create a message object object

            try {
                log.debug("Trying to execute the message response.");
                execute(message); // Sending our message object to user
                log.info("Message response executed with success!");
                log.debug("MESSAGE: {}", message);
            } catch (TelegramApiException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private List<String> getSubRedditsFromCommand(String messageText) {
        List<String> subReddits = new ArrayList<>();

        String[] commandArgs = messageText.substring(COMMAND_NADA_PRA_FAZER.length()+1).split(";");

        for(int x=0; x <commandArgs.length; x++){
            subReddits.add(commandArgs[x]);
        }

        return subReddits;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
