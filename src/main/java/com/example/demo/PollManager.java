package com.example.demo;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {

    private final Map<User, Poll> map = HashMap.newHashMap(2);

    public PollManager() {
    }

    public Map<User, Poll> getMap() {
        return map;
    }
}
