package common.rabbit.publisher;

import java.io.Serializable;

public interface RabbitMQPublisher<T extends Serializable> {

    void publish(T t);
}
