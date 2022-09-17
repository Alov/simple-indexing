package me.alov.simple;

import me.alov.simple.domain.SimpleRecord;

import java.io.IOException;

public interface Crawler {

    SimpleRecord lookup(String url) throws IOException;
}
