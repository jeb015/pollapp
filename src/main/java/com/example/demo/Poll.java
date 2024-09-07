package com.example.demo;

import java.time.Instant;
import java.util.ArrayList;

public class Poll {

    public User creator;

    public String question;

    public Instant publishedAt;

    public Instant validUntil;

    public ArrayList<VoteOption> options;

    public Poll(User creator, String question, Instant validUntil, ArrayList<VoteOption> options) {
        this.creator = creator;
        this.question = question;
        this.publishedAt = Instant.now();
        this.validUntil = validUntil;
        this.options = options;
    }

    public Poll() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
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

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
