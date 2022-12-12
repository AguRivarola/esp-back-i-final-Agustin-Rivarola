package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.SerieEntity;
import com.dh.catalog.model.dto.SerieDto;
import com.dh.catalog.repository.SerieRepositoryMongo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewSerieEventConsumer {

    private final SerieRepositoryMongo serieRepositoryMongo;

    public NewSerieEventConsumer(SerieRepositoryMongo serieRepositoryMongo) {
        this.serieRepositoryMongo = serieRepositoryMongo;
    }
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_SERIE)
    public void execute(SerieDto data) {
        SerieEntity serieNew= new SerieEntity();
        BeanUtils.copyProperties(data,serieNew);
        if (data.getSeasons() != null && serieNew.getSeasons() != null) {
            BeanUtils.copyProperties(data.getSeasons(),serieNew.getSeasons());
        }
        serieRepositoryMongo.delete(data);
        serieRepositoryMongo.save(serieNew);
    }



}
