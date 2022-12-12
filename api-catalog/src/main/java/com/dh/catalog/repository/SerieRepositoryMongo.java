package com.dh.catalog.repository;

import com.dh.catalog.model.SerieEntity;
import com.dh.catalog.model.dto.SerieDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepositoryMongo extends MongoRepository<SerieEntity,Long> {
}