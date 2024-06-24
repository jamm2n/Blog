package com.example.blog.controller;

import com.example.blog.model.BlogPost;
import com.example.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("/")
    public String getAllBlogPosts(Model model) {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        model.addAttribute("blogPosts", blogPosts);
        return "index";
    }

    @GetMapping("/post/{id}")
    public String getBlogPostById(@PathVariable Long id, Model model) {
        BlogPost blogPost = blogPostService.getBlogPostById(id).orElse(null);
        model.addAttribute("blogPost", blogPost);
        return "post";
    }

    @GetMapping("/create")
    public String createBlogPostForm(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "create";
    }

    @PostMapping("/create")
    public String createBlogPost(@ModelAttribute BlogPost blogPost) {
        blogPostService.saveBlogPost(blogPost);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return "redirect:/";
    }
}
