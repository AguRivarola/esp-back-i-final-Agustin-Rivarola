package com.dh.series.service;

import com.dh.series.event.NewSerieEventProducer;
import com.dh.series.model.Serie;
import com.dh.series.repository.SerieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {


    private final SerieRepository serieRepository;
    private final MongoTemplate mongoTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    private final NewSerieEventProducer newSerieEventProducer;

    @Autowired
    public SerieService(SerieRepository serieRepository, MongoTemplate mongoTemplate, RabbitTemplate rabbitTemplate, ObjectMapper mapper, NewSerieEventProducer newSerieEventProducer) {
        this.serieRepository = serieRepository;
        this.mongoTemplate=mongoTemplate;
        this.rabbitTemplate=rabbitTemplate;
        this.mapper=mapper;
        this.newSerieEventProducer = newSerieEventProducer;
    }

    public List<Serie> findByGenre(String genre) {
        return serieRepository.findByGenre(genre);
    }

    public Serie save(Serie serieNew) {
        newSerieEventProducer.execute(serieNew);
        return serieRepository.save(serieNew);
    }
}