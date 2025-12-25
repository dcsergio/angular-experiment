package it.sdc.restserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.sdc.restserver.Jokes;
import it.sdc.restserver.dto.TelegramSendMessageRequest;
import it.sdc.restserver.dto.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Jokes jokes;

    @Value("${telegram.botUrl}")
    private String botUrl;

    @Value("${telegram.botName}")
    private String botName;

    public void sendResponse(Update update) throws JsonProcessingException {

        log.info("""
                incoming json:
                {}
                """, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(update));
        String inputText;

        Update.Message message = update.message();


        if (update.callbackQuery() != null) {
            inputText = update.callbackQuery().data();
        } else if (message != null) {
            if (message.leftChatMember() != null && message.leftChatMember().username().equals(botName)) {
                //they kicked me out
                return;
            }
            if (message.text() != null) {

                inputText = message.text();
            } else if (message.sticker() != null) {
                inputText = "sticker";
            } else {
                inputText = "no text";
            }
        } else {
            inputText = "no message";
        }


        if (message != null) {
            Long chatId = message.chat().id();


            var builder = TelegramSendMessageRequest.builder().chatId(String.valueOf(chatId));
            builder = createResponseText(inputText, builder);
            TelegramSendMessageRequest requestBody = builder.build();

            log.info("""
                    outgoing json:
                    {}
                    """, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody));
            sendMessage(requestBody);
        }
    }

    private TelegramSendMessageRequest.TelegramSendMessageRequestBuilder createResponseText(String originalText, TelegramSendMessageRequest.TelegramSendMessageRequestBuilder builder) {
        return switch (originalText.toLowerCase()) {
            case "/barzelletta" -> createJokeText(builder);
            default -> builder.text("non ho capito");
        };
    }

    private TelegramSendMessageRequest.TelegramSendMessageRequestBuilder createJokeText(TelegramSendMessageRequest.TelegramSendMessageRequestBuilder builder) {
        var joke = jokes.getRandomJoke();
        String question = joke.getQuestion();
        String answer = joke.getAnswer();
        String safeQuestion = question.replace("?", "\\?").replace(".", "\\.").replace("!", "\\!");
        String safeAnswer = answer.replace("?", "\\?").replace(".", "\\.").replace("!", "\\!");

        return builder.text("*" + safeQuestion + "*\n\n" + "||" + safeAnswer + "||").parseMode("MarkdownV2");
    }

    private void sendMessage(TelegramSendMessageRequest body) {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TelegramSendMessageRequest> entity = new HttpEntity<>(body, headers);

        restTemplate.postForEntity(botUrl, entity, String.class);
    }
}
