package controller;

import model.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IPostService;

import java.time.LocalDateTime;
import java.util.Date;


@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    IPostService postService;

    @GetMapping
    public ModelAndView show(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("posts", postService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/post/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Post post) {
        post.setCreateAt(LocalDateTime.now());
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/edit");
        Post post = postService.findById(id).get();
        modelAndView.addObject("po", post);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView update(@PathVariable Long id, Post post) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.save(post);
        return modelAndView;
    }
@PostMapping("/search")
    public ModelAndView search(ModelAndView modelAndView, @RequestParam String title, @RequestParam String dateFrom, @RequestParam String dateTo, @PageableDefault(value = 4) Pageable pageable) {
    if ((dateFrom.equals("") && dateTo.equals(""))) {
        dateFrom = "1900-01-01T00:00:00";
        dateTo = String.valueOf(LocalDateTime.now());
    }
    Page<Post> post = postService.findAllByTitle("%"+title+"%", LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo), pageable);
    modelAndView = new ModelAndView("/post/search");
    modelAndView.addObject("po", post);
    return modelAndView;
}


    @PostMapping("/asc")
    public ModelAndView asc(@PageableDefault(value = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/post/asc");
        Page<Post> post = postService.showListAsc(pageable);
        modelAndView.addObject("po", post);
        return modelAndView;
    }

    @PostMapping("/top5desc")
    public ModelAndView top5desc(@PageableDefault(value = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/post/top5desc");
        Page<Post> post = postService.showListOfCreateAtAsc(pageable);
        modelAndView.addObject("po", post);
        return modelAndView;
    }

}