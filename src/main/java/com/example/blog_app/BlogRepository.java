package com.example.blog_app;

import java.util.Optional;
import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {

    private final JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Blog> findAll() {
        return jdbcClient.sql("SELECT id, title, notes FROM blogs")
            .query(Blog.class)
            .list();
   
    }

    public Optional<Blog> findById(Long id) {
        return jdbcClient.sql("SELECT id, title, notes FROM blogs WHERE id = :id")
            .param("id", id)
            .query(Blog.class)
            .optional();
   
    }

    public void save(Blog blog) {
    jdbcClient.sql("INSERT INTO blogs (id, title, notes) VALUES (:id, :title, :notes)")
        .param("id", blog.getId())
        .param("title", blog.getTitle())
        .param("notes", blog.getNotes())
        .update();
    }


}
