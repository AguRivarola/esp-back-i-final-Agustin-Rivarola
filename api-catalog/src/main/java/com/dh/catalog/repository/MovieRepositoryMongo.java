package com.dh.catalog.repository;

import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.model.dto.MovieDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepositoryMongo extends MongoRepository<MovieEntity,Long> {
    ResponseEntity<List<MovieDto>> findAll(MovieDto consulta);
}
