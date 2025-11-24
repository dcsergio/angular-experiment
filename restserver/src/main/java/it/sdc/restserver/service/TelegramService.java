package it.sdc.restserver.service;

import it.sdc.restserver.dto.TelegramSendMessageRequest;
import it.sdc.restserver.dto.TelegramUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TelegramService {

    private final RestTemplate restTemplate;


    public void echoUppercase(TelegramUpdateRequest update) {
        if (update.message() == null || update.message().text() == null) {
            return;
        }

        String originalText = update.message().text();
        String upperText = originalText.toUpperCase();

        Long chatId = update.message().chat().id();

        TelegramSendMessageRequest requestBody =
                new TelegramSendMessageRequest(
                        String.valueOf(chatId),
                        upperText,
                        null,
                        "sendMessage"
                );

        sendMessage(requestBody);
    }

    private void sendMessage(TelegramSendMessageRequest body) {
        String botToken = "492578505:AAEpk5TWOUae2LxmVZz3sMIZi-z9km4oXQY";
        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TelegramSendMessageRequest> entity = new HttpEntity<>(body, headers);

        restTemplate.postForEntity(url, entity, String.class);
    }
}
