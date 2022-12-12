package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.model.dto.MovieDto;
import com.dh.catalog.repository.MovieRepositoryMongo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class NewMovieEventConsumer {

    private final MovieRepositoryMongo movieRepositoryMongo;

    public NewMovieEventConsumer(MovieRepositoryMongo movieRepositoryMongo) {
        this.movieRepositoryMongo = movieRepositoryMongo;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_MOVIE)
    public void execute(MovieDto data) {
        MovieEntity movieNew= new MovieEntity();
        BeanUtils.copyProperties(data,movieNew);
        movieRepositoryMongo.deleteById(data.getId());
        movieRepositoryMongo.save(movieNew);
    }


}
