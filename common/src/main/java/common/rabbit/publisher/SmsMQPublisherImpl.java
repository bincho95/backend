package common.rabbit.publisher;

import common.rabbit.model.MessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SmsMQPublisherImpl implements SmsMQPublisher {

    private final RabbitTemplate rabbitTemplate;

    public SmsMQPublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(MessageDto messageDto) {
        rabbitTemplate.convertAndSend("smsQueue", messageDto);
    }
}
