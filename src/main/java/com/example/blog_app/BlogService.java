package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> findAll(){
        return blogRepository.findAll();
    }

    public Optional<Blog> findById(Long id){
        return blogRepository.findById(id);
    }

    public void add(BlogForm form){
        if (form.getTitle() == null) {
             throw new IllegalArgumentException("タイトルが空です");
        }

        if (form.getNotes() == null) {
             throw new IllegalArgumentException("本文が空です");
        }
        
        blogRepository.save(new Blog(null, form.getTitle(), form.getNotes()));

    }


}
