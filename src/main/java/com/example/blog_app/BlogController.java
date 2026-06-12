package com.example.blog_app;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    public String blogs(Model model){
        model.addAttribute("blogs", blogService.findAll());
        return "blogs";
    }

    @GetMapping("/blog/{id}")
    public String blogabout(@PathVariable long id, Model model){
        Optional<Blog> blogOpt = blogService.findById(id);
        if(blogOpt.isEmpty()){
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blogOpt.get());
        return "blog/detail";
    }

    @GetMapping("/blog/new")
    public String blognew() {
        return "blog/new";
    }


    @PostMapping("/blogs")
    public String create(@ModelAttribute BlogForm blogForm) {
        blogService.add(blogForm);
        
        return "redirect:/blogs";
    }


}
