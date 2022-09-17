package me.alov.simple;

import lombok.extern.slf4j.Slf4j;
import me.alov.simple.domain.SimpleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class SimpleApplication implements CommandLineRunner {

    @Autowired
    CrawlerService crawlerService;

    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        crawlerService.linkTravers("https://example.com");
        log.info("{}", crawlerService.getVisitedUrls());
        log.info("*-**********************************");
        log.info("*-**********************************");
        log.info("*-**********************************");
        log.info("*-**********************************");
        log.info("*-**********************************");
        log.info("*-**********************************");
        log.info("*-**********************************");
        log.info("*-**********************************");

        log.info("{}", crawlerService.find("KINDNS"));

    }


}
