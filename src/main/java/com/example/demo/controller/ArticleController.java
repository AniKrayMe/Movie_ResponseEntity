package com.example.demo.controller;

import com.example.demo.moddel.Article;
import com.example.demo.moddel.User;
import com.example.demo.sevice.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "article/create")
    public void create(@RequestBody Article article) {
        articleService.create(article);
    }


    @GetMapping(value = "article/find/{id}")
    public void get(@PathVariable int id) {
        articleService.findById(id);
    }
    @GetMapping(value = "/article")
    public ArrayList<Article> getAll(){
        return articleService.findAll();
    }
    @PutMapping(value = "/article/{id}")
    public void update(@PathVariable int id ,@RequestBody Article article){
        articleService.updateById(id, article);
    }
    @DeleteMapping(value = "/article/{id}")
    public void delete(@PathVariable int id){
        articleService.deleteById(id);
    }
}

