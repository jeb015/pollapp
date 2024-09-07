package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DomainManager {

    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Poll> polls = new ArrayList<>();
    private final ArrayList<Vote> votes = new ArrayList<>();
    private final ArrayList<VoteOption> voteOptions = new ArrayList<>();

    public DomainManager() {
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Poll> getPolls() {
        return polls;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public ArrayList<VoteOption> getVoteOptions() {
        return voteOptions;
    }
}
