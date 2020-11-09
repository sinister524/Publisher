package com.sinister524.Publisher.Services;

import com.sinister524.Publisher.Entitys.Action;
import com.sinister524.Publisher.Entitys.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;

@Service
public class MessageService {

    @Value("${url}")
    private String url;

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    private final Random random = new Random();

    private Long msisdn = (long) random.nextInt(1000000000);

    public List<Message> getMessages(){
        logger.info("Getting messages from SUBSCRIBER. URL - " + url);
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(url, Message[].class)));
    }

    public Long getMsisdn() {
        return msisdn;
    }

    public void generateNewMsisdn () {
        msisdn = (long) random.nextInt(1000000000);
        logger.info("Generated new MSISDN, value: " + msisdn);
    }

    public void sendMessage(){
        Action action = Action.PURCHASE;
        if (random.nextBoolean()) {
            action = Action.SUBSCRIPTION;
        }
        Message message = new Message(msisdn, action, Instant.now().getEpochSecond());
        restTemplate.postForObject(url, message, Message.class);
        logger.info("Sending message to SUBSCRIBER. URL - " + url + " " + message.toString());
    }
}
