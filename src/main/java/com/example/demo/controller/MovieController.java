package com.example.demo.controller;

import com.example.demo.moddel.Movie;
import com.example.demo.sevice.MovieService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/movie")
    public void create(@RequestBody Movie movie) {
        movieService.create(movie);
    }

    @GetMapping(value = "/movie/{id}")
    public ResponseEntity<Movie> get(@PathVariable int id) {
        Movie movieByID = movieService.findById(id);
        return ResponseEntity.ok(movieByID);
    }
    @GetMapping(value = "/movie")
    public ResponseEntity<ArrayList<Movie>> getAll(){
        ArrayList<Movie> movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }
    @PutMapping(value = "/movie/{id}")
    public void update(@PathVariable int id , @RequestBody  Movie movie){
        movieService.updateById(id, movie);
    }
    @DeleteMapping(value = "/movie/{id}")
    public void delete(@PathVariable int id){
        movieService.deleteById(id);
    }
}
