package com.dh.catalog.service;


import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.model.dto.CatalogDto;
import com.dh.catalog.model.dto.MovieDto;
import com.dh.catalog.model.dto.SerieDto;
import com.dh.catalog.repository.MovieRepositoryMongo;
import com.dh.catalog.repository.SerieRepositoryMongo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CatalogService {

    private final MovieRepositoryMongo movieRepositoryMongo;
    private final SerieRepositoryMongo serieRepositoryMongo;

    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;

    public CatalogService(MovieRepositoryMongo movieRepositoryMongo, SerieRepositoryMongo serieRepositoryMongo, MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.movieRepositoryMongo = movieRepositoryMongo;
        this.serieRepositoryMongo = serieRepositoryMongo;
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }

    public CatalogDto getCatalogByGenre(String genre) {
        CatalogDto response = new CatalogDto();
        findAllSerieByGenre(genre, response);
        findAllMovieByGenre(genre, response);
        return response;
    }

    @Retry(name = "retryCall")
    @CircuitBreaker(name = "clientApi", fallbackMethod = "findAllMovieFallBack")
    private void findAllMovieByGenre(String genre, CatalogDto response) {
        var movieList = movieServiceClient.getAll().stream().filter(movie -> movie.getGenre().equals(genre)).collect(Collectors.toList());
        response.setMovieDtoList(movieList.stream().map(movie -> {
            MovieDto movieResponse = new MovieDto();
            BeanUtils.copyProperties(movie, movieResponse);
            return movieResponse;
        }).collect(Collectors.toList()));
    }

    public void findAllMovieFallBack(String genre, CatalogDto response, Throwable t) {
        var movieFilter = movieRepositoryMongo.findAll().stream().filter(movie -> movie.getGenre().equals(genre)).collect(Collectors.toList());
        response.setMovieDtoList(movieFilter.stream().map(movie -> {
            MovieDto musicResponse = new MovieDto();
            BeanUtils.copyProperties(movie, musicResponse);
            return musicResponse;
        }).collect(Collectors.toList()));
    }


    @CircuitBreaker(name = "clientApi", fallbackMethod = "findAllSerieFallBack")
    private void findAllSerieByGenre(String genre, CatalogDto response) {
        var serieList = serieServiceClient.getAll().stream().filter(serie -> serie.getGenre().equals(genre)).collect(Collectors.toList());
        response.setSerieDtoList(serieList.stream().map(serie -> {
            SerieDto serieResponse = new SerieDto();
            BeanUtils.copyProperties(serie, serieResponse);
            return serieResponse;
        }).collect(Collectors.toList()));
    }

    private void findAllSerieFallBack(String genre, CatalogDto response, Throwable t) {
        var serieList = serieServiceClient.getAll().stream().filter(serie -> serie.getGenre().equals(genre)).collect(Collectors.toList());
        var serieFilter = serieRepositoryMongo.findAll().stream().filter(serie -> serie.getGenre().equals(genre)).collect(Collectors.toList());
        response.setSerieDtoList(serieFilter.stream().map(serie -> {
            SerieDto serieResponse = new SerieDto();
            BeanUtils.copyProperties(serie, serieResponse);
            return serieResponse;
        }).collect(Collectors.toList()));
    }

    public CatalogDto getCatalogoByGenreOfline(String genre) {
        CatalogDto response = new CatalogDto();
        var movieFilter = movieRepositoryMongo.findAll().stream().filter(movie -> movie.getGenre().equals(genre)).collect(Collectors.toList());
        response.setMovieDtoList(movieFilter.stream().map(movie -> {
            MovieDto musicResponse = new MovieDto();
            BeanUtils.copyProperties(movie, musicResponse);
            return musicResponse;
        }).collect(Collectors.toList()));

        var serieFilter = serieRepositoryMongo.findAll().stream().filter(serie -> serie.getGenre().equals(genre)).collect(Collectors.toList());
        response.setSerieDtoList(serieFilter.stream().map(serie -> {
            SerieDto serieResponse = new SerieDto();
            BeanUtils.copyProperties(serie, serieResponse);
            return serieResponse;
        }).collect(Collectors.toList()));
        return response;
    }
}
