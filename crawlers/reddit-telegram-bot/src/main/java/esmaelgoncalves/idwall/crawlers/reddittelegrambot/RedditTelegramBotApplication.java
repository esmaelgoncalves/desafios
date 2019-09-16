package esmaelgoncalves.idwall.crawlers.reddittelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class RedditTelegramBotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();

		SpringApplication.run(RedditTelegramBotApplication.class, args);
	}

}
