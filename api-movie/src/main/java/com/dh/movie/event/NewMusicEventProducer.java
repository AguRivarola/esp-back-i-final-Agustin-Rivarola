package com.dh.movie.event;

import com.dh.movie.config.RabbitMQConfig;
import com.dh.movie.model.Movie;
import com.dh.movie.model.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;



@Component
@Slf4j
public class NewMusicEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public NewMusicEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void execute(Movie movieNew) {
        MovieDto data = new MovieDto();
        BeanUtils.copyProperties(movieNew,data);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NEW_MOVIE, data);
    }


}
