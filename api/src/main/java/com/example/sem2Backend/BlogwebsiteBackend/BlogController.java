package com.example.sem2Backend.BlogwebsiteBackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BlogController {

    @GetMapping("/health")
    public String sayHello() {
        return "Server is running";
    }

    // Example: Get a blog post (for demonstration, returning a static string)
    @GetMapping("/posts")
    public String getPosts() {
        return "List of blog posts";
    }

    // Example: Create a blog post
    @PostMapping("/posts")
    public String createPost(@RequestBody String postContent) {
        return "Created post: " + postContent;
    }
}