package common.rabbit.publisher;

import common.rabbit.model.MessageDto;

public interface SmsMQPublisher extends RabbitMQPublisher<MessageDto>{
    void publish(MessageDto messageDto);
}
