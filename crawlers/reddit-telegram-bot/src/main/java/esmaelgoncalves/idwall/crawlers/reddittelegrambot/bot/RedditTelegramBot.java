package esmaelgoncalves.idwall.crawlers.reddittelegrambot.bot;

import esmaelgoncalves.idwall.crawlers.reddittelegrambot.model.ThreadReddit;
import esmaelgoncalves.idwall.crawlers.reddittelegrambot.service.WebCrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class RedditTelegramBot extends TelegramLongPollingBot {

    public static final String COMMAND_NADA_PRA_FAZER = "/nadaprafazer";
    public static final String COMMAND_START = "/start";
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;

    @Autowired
    private WebCrawlerService webCrawlerService;

    @Override
    public void onUpdateReceived(Update update) {
        log.trace("Receiving a update event: {}", update);
        if (update.hasMessage() && update.getMessage().hasText()) {

            // Set variables
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            log.debug("Received message: {} from ChatID: {}", messageText, chatId);

            if(messageText.startsWith(COMMAND_START)){
                log.debug("Known command: {}", COMMAND_START);
                SendMessage message = new SendMessage();
                message.enableMarkdown(true);

                message.setChatId(chatId);
                message.setText("Olá, bem vindo!\n" +
                        "Saiba quais as threads estão bombando no Reddit.\n" +
                        "Use: /nadaprafazer nome_subreddit1;nome_subrredit2");

                sendResponseMessage(message);
            } else if (messageText.startsWith(COMMAND_NADA_PRA_FAZER)) {
                log.debug("Known command: {}", COMMAND_NADA_PRA_FAZER);

                SendMessage message = new SendMessage();
                message.enableMarkdown(true);
                message.setParseMode("Markdown");

                List<String> subreddits = getSubRedditsFromCommand(messageText);

                message.setChatId(chatId);
                message.setText("*Aguarde*, estamos buscando o melhor conteúdo para você!");
                sendResponseMessage(message);


                Set<ThreadReddit> threadReddits = webCrawlerService.getThreadInfo(subreddits);

                if(threadReddits.size() > 0) {
                    message.setChatId(chatId);
                    StringBuilder responseBuilder = new StringBuilder();
                    responseBuilder.append("Threads que estão bombando:").append("\n\n");
                    threadReddits.forEach(t -> {
                        responseBuilder.append("*Título*: " + t.getTitle()).append("\n");
                        responseBuilder.append("*Sub Reddit*: " + t.getSubReddit()).append("\n");
                        responseBuilder.append("*Votos*: " + t.getPoints()).append("\n");
                        responseBuilder.append("Acesse a Thread [AQUI](" + t.getThreadUrl() +")").append("\n");
                        responseBuilder.append("Veja os [comentarios](" + t.getCommentsUrl() + ")").append("\n");
                        responseBuilder.append("\n");
                    });

                    message.setText(responseBuilder.toString());
                } else {
                    message.setChatId(chatId);
                    message.setText("Nenhum conteúdo interessante encontrado. :(");
                }
                sendResponseMessage(message);
            } else {
                SendMessage message = new SendMessage();
                message.enableMarkdown(true);

                log.info("Unknown command executed.");
                message.setChatId(chatId);
                message.setText("Comando não reconhecido.");
                sendResponseMessage(message);
            }
        }
    }

    private void sendResponseMessage(SendMessage message) {
        try {
            log.debug("Trying to execute the message response.");
            execute(message); // Sending our message object to user
            log.info("Message response executed with success!");
            log.debug("MESSAGE: {}", message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
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
