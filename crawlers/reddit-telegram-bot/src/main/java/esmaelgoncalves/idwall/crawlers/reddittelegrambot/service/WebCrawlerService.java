package esmaelgoncalves.idwall.crawlers.reddittelegrambot.service;

import esmaelgoncalves.idwall.crawlers.reddittelegrambot.dto.ThreadReddit;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Service
public class WebCrawlerService {

    @Value("${reddit.url.base}")
    private String baseUrl;

    public Set<ThreadReddit> getThreadsInfo(String threads) {

        List<String> subReddits = new ArrayList<>();

        String[] commandArgs = threads.split(";");

        for (int x = 0; x < commandArgs.length; x++) {
            subReddits.add(commandArgs[x]);
        }

        return getThreadsInfo(subReddits);

    }

    public Set<ThreadReddit> getThreadsInfo(List<String> threads) {

        Set<ThreadReddit> response = new TreeSet<>();

        threads.parallelStream().forEach(t -> {
            String URL = baseUrl + "/r/" + t;

            try {
                log.debug("Trying to connect to: {}", URL);
                String html = Jsoup.connect(URL).get().html();
                log.trace("HTML REUSLT:\n{}", html);
                Document doc = Jsoup.parse(html);

                Element siteTable = doc.getElementById("siteTable");
                Elements subReddits = siteTable.children();

                for (Element subReddit : subReddits) {

                    String votesString = subReddit.getElementsByClass("score unvoted").attr("title");
                    int votes = 0;
                    if (!"".equals(votesString) ) {
                        votes = Integer.valueOf(votesString);
                    }

                    if (votes > 5000) {
                        String title = subReddit.select("div.entry.unvoted > div.top-matter > p.title > a").text();
                        String threadUrl = subReddit.select("div.entry.unvoted > div.top-matter > p.title > a").attr("href");
                        String commentsUrl = subReddit.select("div.entry.unvoted > div.top-matter > ul > li.first > a").attr("href");

                        if (threadUrl.startsWith("/r/")) {
                            threadUrl = baseUrl + threadUrl;
                        }

                        ThreadReddit threadReddit = ThreadReddit.builder()
                                .title(title)
                                .subReddit(t)
                                .threadUrl(threadUrl)
                                .commentsUrl(commentsUrl)
                                .points(String.valueOf(votes)).build();

                        log.debug("New Thread: {}", threadReddit);

                        response.add(threadReddit);
                    }

                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });


        return response;
    }

}
