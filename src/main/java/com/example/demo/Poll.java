package com.example.demo;

import java.time.Instant;
import java.util.ArrayList;

public class Poll {

    public User creator;

    public String question;

    public ArrayList<VoteOption> options;

    public Poll(User creator, String question, ArrayList<VoteOption> options) {
        this.creator = creator;
        this.question = question;
        this.options = options;
    }

    public Poll() {
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<VoteOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<VoteOption> options) {
        this.options = options;
    }

    public User getCreator() {
        return creator;
    }
}
