package com.example.demo;

import java.time.Instant;

public class Vote {

    public User voter;

    public String question;

    public String answer;

    public Instant publishedAt;

    public Vote(User voter, String question, String answer) {
        this.voter = voter;
        this.question = question;
        this.answer = answer;
        this.publishedAt = Instant.now();
    }

    public Vote() {
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }
}
