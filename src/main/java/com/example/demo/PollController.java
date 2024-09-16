package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@CrossOrigin
public class PollController {

    public final PollManager manager;

    public final DomainManager domainManager;

    public PollController(@Autowired PollManager manager, DomainManager domainManager) {
        this.manager = manager;
        this.domainManager = domainManager;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> users(@PathVariable String username) {
        for (User usr : domainManager.getUsers()) {
            if (usr.getName().equals(username)) {
                return ResponseEntity.ok(usr);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> addUser(@RequestBody User input){
        for (User user : domainManager.getUsers()) {
            if (user.getEmail().equals(input.getEmail())) {
                if (user.getName().equals(input.getName()))
                    return ResponseEntity.ok(input);
                else
                    return ResponseEntity.badRequest().build();
            }
        }
        User newUser = new User(input.name, input.email);
        domainManager.getUsers().add(newUser);
        return ResponseEntity.created(URI.create("/" + newUser.name)).body(newUser);
    }

    @GetMapping("/users")
    public ResponseEntity<ArrayList<User>> getUsers() {
        return ResponseEntity.ok(domainManager.getUsers());
    }

    @GetMapping("/polls")
    public ResponseEntity<ArrayList<Poll>> getPolls() {
        return ResponseEntity.ok(domainManager.getPolls());
    }



    @PostMapping("/createPoll")
    public ResponseEntity<Poll> addPoll(@RequestBody Poll poll) {
            User creator = new User(poll.creator.name, poll.creator.email);
            ArrayList<VoteOption> options = new ArrayList<>();
            for (VoteOption option : poll.options) {
                options.add(new VoteOption(option.caption));
            }
            Poll newPoll = new Poll(creator, poll.question, options);
            domainManager.getPolls().add(newPoll);
            manager.getMap().put(newPoll.getCreator(), newPoll);
            return ResponseEntity.created(URI.create("/" + newPoll.getCreator().name)).body(newPoll);
    }

    @PutMapping("/vote")
    public ResponseEntity<Poll> updatePoll(@RequestBody Vote vote) {
        Vote newVote = new Vote(vote.voter, vote.question, vote.answer);
        for (Poll p : manager.getMap().values()) {
            if (p.getQuestion().equals(newVote.getQuestion())) {
                for (VoteOption ops : p.getOptions()) {
                    if (ops.getCaption().equals(newVote.getAnswer())) {
                        removePrevVote(p, newVote.getVoter());
                        ops.getVotes().add(newVote);
                        return ResponseEntity.ok(p);
                    }
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public String deletePoll(@RequestBody String question) {
        for (Poll p : domainManager.getPolls()) {
            if (p.getQuestion().equals(question)) {
                domainManager.getPolls().remove(p);
                return "Poll Deleted";
            }
        }
        return "Poll not found";
    }

    public void removePrevVote (Poll poll, User user) {
        for (VoteOption ops : poll.getOptions()) {
            for (Vote vote : ops.getVotes()) {
                if (vote.getVoter().getName().equals(user.getName())) {
                    ops.getVotes().remove(vote);
                    break;
                }
            }
        }
    }
}
