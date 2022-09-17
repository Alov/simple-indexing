package me.alov.simple;

import lombok.extern.slf4j.Slf4j;
import me.alov.simple.domain.SimpleRecord;
import me.alov.simple.storage.DummyStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CrawlerService {

    @Autowired
    private Crawler crawler;
    @Autowired
    private DummyStorage dummyStorage;

    private List<String> visitedUrls = new ArrayList<>();


    public List<String> getVisitedUrls() {
        return this.visitedUrls;
    }

    public void linkTravers(String url) throws Exception {
        if (visitedUrls.contains(url)) {
            return;
        }
        if (visitedUrls.size() > 100) {
            return;
        }

        SimpleRecord record = crawler.lookup(url);
        String title = record.getTitle();
        String[] words = title.split(" ");
        for (String word: words) {
            dummyStorage.addByKeyword(word, record);
        }

        visitedUrls.add(url);
        record.getUrls().forEach(link -> {
            try {
                linkTravers(link);
            } catch (Exception e) {
                log.error("Can not proceed link {}", link);
                e.printStackTrace();
            }
        });

    }

    public List<SimpleRecord> find(String keyword) {
        return dummyStorage.findLinkByWord(keyword);
    }

}
