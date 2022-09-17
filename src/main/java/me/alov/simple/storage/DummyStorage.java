package me.alov.simple.storage;

import me.alov.simple.domain.SimpleRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class DummyStorage {

    private HashMap<String, List<SimpleRecord>> data = new HashMap<>();

    public List<SimpleRecord> findLinkByWord(String keyword) {
        return data.get(keyword);
    }

    public void addByKeyword(String keyword, SimpleRecord record) {
        if (data.containsKey(keyword)) {
            List<SimpleRecord> records = data.get(keyword);
            records.add(record);
        } else {
            ArrayList<SimpleRecord> value = new ArrayList<>();
            value.add(record);
            data.put(keyword, value);
        }
    }
}
