package it.sdc.restserver.service;

import it.sdc.restserver.dto.TelegramSendMessageRequest;
import it.sdc.restserver.dto.TelegramUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TelegramService {

    private final RestTemplate restTemplate;

    @Value(value = "${telegram.botToken}")
    private String botToken;

    public void sendResponse(TelegramUpdateRequest update) {
        if (update.message() == null || update.message().text() == null) {
            return;
        }

        String originalText = update.message().text();
        String responseText = createResponseText(originalText);

        Long chatId = update.message().chat().id();

        TelegramSendMessageRequest requestBody =
                new TelegramSendMessageRequest(
                        String.valueOf(chatId),
                        responseText,
                        null,
                        "sendMessage"
                );

        sendMessage(requestBody);
    }

    private static String createResponseText(String originalText) {
        return originalText.toUpperCase();
    }

    private void sendMessage(TelegramSendMessageRequest body) {

        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TelegramSendMessageRequest> entity = new HttpEntity<>(body, headers);

        restTemplate.postForEntity(url, entity, String.class);
    }
}
