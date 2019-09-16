package esmaelgoncalves.idwall.crawlers.reddittelegrambot.config;

import esmaelgoncalves.idwall.crawlers.reddittelegrambot.bot.RedditTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Configuration
public class TelegramConfig {

    @Autowired
    RedditTelegramBot redditTelegramBot;


    @Bean
    public TelegramBotsApi initilize(){
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(redditTelegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return botsApi;
    }
}
