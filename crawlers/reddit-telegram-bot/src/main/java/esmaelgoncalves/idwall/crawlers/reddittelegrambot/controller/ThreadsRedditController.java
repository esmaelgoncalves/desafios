package esmaelgoncalves.idwall.crawlers.reddittelegrambot.controller;

import esmaelgoncalves.idwall.crawlers.reddittelegrambot.dto.ThreadReddit;
import esmaelgoncalves.idwall.crawlers.reddittelegrambot.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/threads")
public class ThreadsRedditController {

    @Autowired
    private WebCrawlerService webCrawlerService;

    @GetMapping("{threads}")
    public ResponseEntity<Set<ThreadReddit>> getThreads(@PathVariable String threads){
        Set<ThreadReddit> responseList = webCrawlerService.getThreadsInfo(threads);

        if(responseList.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(responseList);
        }
    }
}
