package com.example.demo;

import java.util.ArrayList;

public class VoteOption {

    public String caption;

    public ArrayList<Vote> votes;

    public VoteOption(String caption) {
        this.caption = caption;
        this.votes = new ArrayList<>();
    }

    public VoteOption() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }
}
