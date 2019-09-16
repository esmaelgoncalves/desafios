package esmaelgoncalves.idwall.crawlers.reddittelegrambot.service;

import esmaelgoncalves.idwall.crawlers.reddittelegrambot.model.ThreadReddit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebCrawlerService {



    public static void main(String[] args) throws IOException {

        String baseUrl = "https://old.reddit.com";
        String reddit = "/r/cats/";


        String html = Jsoup.connect(baseUrl+reddit).get().html();
        Document doc = Jsoup.parse(html);

        Element siteTable = doc.getElementById("siteTable");
        Elements subReddits = siteTable.children();

        for (Element subReddit : subReddits) {
            try {
                int votes = Integer.valueOf(subReddit.getElementsByClass("score unvoted").text());
                if (votes > 3000) {
                    String title = subReddit.select("div.entry.unvoted > div.top-matter > p.title > a").text();
                    String threadUrl = subReddit.select("div.entry.unvoted > div.top-matter > p.title > a").attr("href");

                    if(threadUrl.startsWith("/r/")){
                        threadUrl= baseUrl + threadUrl;
                    }

                    ThreadReddit threadReddit = ThreadReddit.builder()
                            .title(title)
                            .subReddit(reddit)
                            .threadUrl(threadUrl)
                            .commentsUrl("")
                            .points(String.valueOf(votes)).build();

                    System.out.println(threadReddit);
                }
            } catch(NumberFormatException e){

            }
        }


    }
}
