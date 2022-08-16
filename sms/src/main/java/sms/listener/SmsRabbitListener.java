package sms.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.rabbit.model.MessageDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sms.service.SmsService;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
public class SmsRabbitListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SmsService smsService;

    @RabbitListener(queues = "smsQueue")
    public void consume(@Payload MessageDto messageDto) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        log.info("receive message {}" ,messageDto);
        smsService.sendSms(messageDto);
    }
}
