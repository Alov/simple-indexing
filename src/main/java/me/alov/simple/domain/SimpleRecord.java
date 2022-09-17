package me.alov.simple.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SimpleRecord {
    private String title;
    private String content;
    private List<String> urls = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("[title=%s; links=%s;]", this.title, urls);
    }
}
