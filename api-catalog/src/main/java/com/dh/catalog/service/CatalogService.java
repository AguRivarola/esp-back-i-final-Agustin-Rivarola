package com.dh.catalog.service;


import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.dto.MovieDto;
import com.dh.catalog.repository.MovieRepositoryMongo;
import com.dh.catalog.repository.SerieRepositoryMongo;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CatalogService {

    private final Map<String, Integer> popularidadMg;

    private final MovieRepositoryMongo movieRepositoryMongo;
    private final SerieRepositoryMongo serieRepositoryMongo;

    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;

    public CatalogService(Map<String, Integer> popularidadMg, MovieRepositoryMongo movieRepositoryMongo, SerieRepositoryMongo serieRepositoryMongo, MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.popularidadMg = popularidadMg;
        this.movieRepositoryMongo = movieRepositoryMongo;
        this.serieRepositoryMongo = serieRepositoryMongo;
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }

    @CircuitBreaker(name = "clientApi", fallbackMethod = "moviesFallbackMethod")
    public ResponseEntity<List<MovieDto>> findMovieByGenre(String genre) {
        MovieDto consulta = new MovieDto();
        consulta.setGenre(genre);
        List<MovieDto> listMovies= movieServiceClient.getMovieByGenre(genre);
        ResponseEntity<List<MovieDto>> listResponseEntity =
        return listMovies;
    }

    private ResponseEntity<List<MovieDTO>> moviesFallbackMethod(CallNotPermittedException exception) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }


    @CircuitBreaker(name = "clientApi", fallbackMethod = "seriesFallbackMethod")
    public ResponseEntity<List<SeriesDTO>> findSeriesByGenre(String genre, Boolean throwError) {
        ResponseEntity<List<SeriesDTO>> listSeries= seriesClient.getSeriesByGenreWithThrowError(genre, throwError);
        SeriesHistory seriesHistory= new SeriesHistory();
        seriesHistory.setSeries(listSeries.getBody());
        seriesHistory.setGenre(genre);
        seriesHistory.setDate(LocalDateTime.now());
        seriesHistoryRepository.save(seriesHistory);
        return listSeries;
    }

    private ResponseEntity<List<SeriesDTO>> seriesFallbackMethod(CallNotPermittedException exception) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @RabbitListener(queues = "${queue.movie.name}")
    public void saveMovie(MovieDTO movieDTO) throws Exception {
        String genre= movieDTO.getGenre();
        CatalogDTO catalogDTO= getCatalogByGenre(genre);
        if (Objects.nonNull(catalogDTO)){
            List<MovieDTO>movies= catalogDTO.getMovies();
            movies.add(movieDTO);
            catalogDTO.setMovies(movies);
            updateCatalog(catalogDTO);
        }else {
            CatalogDTO catalogDTO1= new CatalogDTO();
            catalogDTO1.setGenre(movieDTO.getGenre());
            List<MovieDTO>movies= new ArrayList<>();
            movies.add(movieDTO);
            catalogDTO1.setMovies(movies);
            saveNewCatalog(catalogDTO1);
        }

    }

    @RabbitListener(queues = "${queue1.series.name}")
    public void saveSeries(SeriesDTO seriesDTO) throws Exception {

        String genre= seriesDTO.getGenre();
        CatalogDTO catalogDTO= getCatalogByGenre(genre);
        if (Objects.nonNull(catalogDTO)){
            List<SeriesDTO>series= catalogDTO.getSeries();
            series.add(seriesDTO);
            catalogDTO.setSeries(series);
            updateCatalog(catalogDTO);
        }else {
            CatalogDTO catalogDTO1= new CatalogDTO();
            catalogDTO1.setGenre(seriesDTO.getGenre());
            List<SeriesDTO>series= new ArrayList<>();
            series.add(seriesDTO);
            catalogDTO1.setSeries(series);
            saveNewCatalog(catalogDTO1);
        }

    }


    public void saveNewCatalog(CatalogDTO catalogDTO){
        Catalog catalog=mapper.convertValue(catalogDTO, Catalog.class);
        catalogRepository.insert(catalog);
    }

    public void updateCatalog(CatalogDTO catalogDTO){
        Catalog catalog=mapper.convertValue(catalogDTO, Catalog.class);
        catalogRepository.save(catalog);
    }

    public List<MovieHistoryDTO> getMovieHistory(){
        List<MovieHistory>movieHistories=movieHistoryRepository.findAll();
        List<MovieHistoryDTO>movieHistoryDTOS=new ArrayList<>();
        for (MovieHistory movieHistory:movieHistories) {
            movieHistoryDTOS.add(mapper.convertValue(movieHistory, MovieHistoryDTO.class));
        }
        return movieHistoryDTOS;
    }

    public List<SeriesHistoryDTO> getSeriesHistory(){
        List<SeriesHistory>seriesHistories=seriesHistoryRepository.findAll();
        List<SeriesHistoryDTO>seriesHistoryDTOS=new ArrayList<>();
        for (SeriesHistory seriesHistory:seriesHistories) {
            seriesHistoryDTOS.add(mapper.convertValue(seriesHistory, SeriesHistoryDTO.class));
        }
        return seriesHistoryDTOS;
    }
}
