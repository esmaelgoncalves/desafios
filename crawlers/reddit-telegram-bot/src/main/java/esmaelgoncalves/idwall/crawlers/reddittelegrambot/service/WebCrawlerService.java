package esmaelgoncalves.idwall.crawlers.reddittelegrambot.service;

import esmaelgoncalves.idwall.crawlers.reddittelegrambot.model.ThreadReddit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class WebCrawlerService {

    @Value("${reddit.url.base}")
    private String baseUrl;


    public Set<ThreadReddit> getThreadInfo(List<String> threads) {

        Set<ThreadReddit> response = new TreeSet<>();

        threads.parallelStream().forEach(t -> {
            String URL = baseUrl + "/r/" + t;

            try {
                String html = Jsoup.connect(URL).get().html();
                Document doc = Jsoup.parse(html);

                Element siteTable = doc.getElementById("siteTable");
                Elements subReddits = siteTable.children();

                for (Element subReddit : subReddits) {
                    try {
                        int votes = Integer.valueOf(subReddit.getElementsByClass("score unvoted").text());
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

                            response.add(threadReddit);
                        }
                    } catch (NumberFormatException e) {

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        });



        return response;
    }

}
