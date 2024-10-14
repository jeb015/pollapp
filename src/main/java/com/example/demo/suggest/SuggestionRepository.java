package com.example.demo.suggest;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SuggestionRepository {

    private final Map<String, Suggestion> map = HashMap.newHashMap(2);

    public SuggestionRepository() {
        map.put("bergen", new Suggestion("sunglasses", 43));
        map.put("oslo", new Suggestion("umbrella", 3));
    }

    public Map<String, Suggestion> getMap() {
        return map;
    }
}
