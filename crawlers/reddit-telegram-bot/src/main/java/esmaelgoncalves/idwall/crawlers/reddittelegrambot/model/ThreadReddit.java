package esmaelgoncalves.idwall.crawlers.reddittelegrambot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThreadReddit {

    private String title;
    private String subReddit;
    private String points;
    private String threadUrl;
    private String commentsUrl;
}
