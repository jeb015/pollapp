package com.example.demo.suggest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/suggest")
public class SuggestionController {

    public final SuggestionRepository repo;

    public SuggestionController(@Autowired SuggestionRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{location}")
    public ResponseEntity<Suggestion> suggest(@PathVariable String location) {
        System.out.println(location);
        if (repo.getMap().containsKey(location)) {
            return ResponseEntity.ok(repo.getMap().get(location));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{location}")
    public ResponseEntity<Suggestion> create(@PathVariable String location,@RequestBody Suggestion suggestion) {
        repo.getMap().put(location, suggestion);
        return ResponseEntity.created(URI.create("/" + location)).body(suggestion);
    }
}
