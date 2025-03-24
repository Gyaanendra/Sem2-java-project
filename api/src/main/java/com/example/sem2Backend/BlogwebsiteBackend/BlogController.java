package com.example.sem2Backend.BlogwebsiteBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/health")
    public String sayHello() {
        return "Server is running";
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Optional: Keep the POST method to add new posts
    @PostMapping("/save_posts")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        try {
            postRepository.save(post);
            return new ResponseEntity<>("Blog posted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to post blog: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}