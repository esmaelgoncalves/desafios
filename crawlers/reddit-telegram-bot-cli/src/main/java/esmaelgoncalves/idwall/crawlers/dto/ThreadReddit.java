package esmaelgoncalves.idwall.crawlers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThreadReddit implements Comparable<ThreadReddit> {

    private String title;
    private String subReddit;
    private String points;
    private String threadUrl;
    private String commentsUrl;

    @Override
    public int compareTo(ThreadReddit o) {
        return Integer.valueOf(o.getPoints()).compareTo(Integer.valueOf(points));
    }
}
