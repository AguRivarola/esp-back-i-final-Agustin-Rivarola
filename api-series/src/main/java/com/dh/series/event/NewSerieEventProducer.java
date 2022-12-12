package com.dh.series.event;

import com.dh.series.config.RabbitMQSenderConfig;
import com.dh.series.model.Serie;
import com.dh.series.model.dto.SerieDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
@Slf4j
public class NewSerieEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public NewSerieEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void execute(Serie serieNew){
        SerieDto data = new SerieDto();
        BeanUtils.copyProperties(serieNew,data);
        rabbitTemplate.convertAndSend(RabbitMQSenderConfig.EXCHANGE_NAME, RabbitMQSenderConfig.TOPIC_NEW_SERIE, data);
    }


}
