package me.alov.simple;

import lombok.extern.slf4j.Slf4j;
import me.alov.simple.domain.SimpleRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CrawlerImpl implements Crawler {

    private static final String HREF_ATTR = "href";
    private static final List<String> IGNORED = List.of("about/");


    @Override
    public SimpleRecord lookup(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        SimpleRecord record = new SimpleRecord();
        record.setTitle(doc.title());
        record.setContent(doc.body().toString());
        List<String> foreignLink = composeUrls(url, doc.select("a"));
        record.setUrls(foreignLink);

        log.info("Looking up for link: {}", url);
        log.info("Result {}", record);

        return record;
    }

    private List<String> composeUrls(String url, Elements elements) throws MalformedURLException {
        List<String> result = new ArrayList<>();
        URL compiledUrl = new URL(url);
        String schemeAndHost = compiledUrl.getProtocol() + "://" + compiledUrl.getHost();

        elements.forEach(element -> {
            String fullLink;
            String link = element.attr(HREF_ATTR);

            if (link.startsWith("http")) {
                fullLink = link;
            } else {
                if (link.startsWith("/")) {
                    fullLink = schemeAndHost+link;
                } else {
                    fullLink = schemeAndHost + "/" + link;
                }
            }
            result.add(fullLink);
        });
        return result;
    }
}
