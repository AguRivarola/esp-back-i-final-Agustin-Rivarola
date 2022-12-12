package com.dh.series.controller;

import com.dh.series.event.NewSerieEventProducer;
import com.dh.series.model.Serie;
import com.dh.series.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v2/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }


    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(serieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Serie> saveMovie(@RequestBody Serie movie) {
        return ResponseEntity.ok().body(serieService.save(movie));
    }
}