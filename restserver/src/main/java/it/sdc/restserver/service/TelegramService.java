package it.sdc.restserver.service;

import it.sdc.restserver.dto.TelegramSendMessageRequest;
import it.sdc.restserver.dto.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramService {

    private final RestTemplate restTemplate;

    @Value("${telegram.botUrl}")
    private String botUrl;

    public void sendResponse(Update update) {
        String inputText;

        if (update.callbackQuery() != null) {
            inputText = update.callbackQuery().data();
        } else if (update.message() != null && update.message().text() != null) {
            inputText = update.message().text();
        } else {
            return;
        }

        String responseText = createResponseText(inputText);

        Long chatId = update.message().chat().id();

        var keyboard = makeKeyboard(List.of("mario", "luigi", "antonio"), 3);

        TelegramSendMessageRequest requestBody =
                TelegramSendMessageRequest.builder()
                        .chatId(String.valueOf(chatId))
                        .text(responseText)
                        .replyMarkup(TelegramSendMessageRequest.ReplyMarkup.builder()
                                .inlineKeyboard(keyboard.inlineKeyboard())
                                .build())
                        .build();

        sendMessage(requestBody);
    }

    private static String createResponseText(String originalText) {
        return switch (originalText.toLowerCase()) {
            case "do", "dom", "domenica" -> "DOMENICA";
            case "lu", "lun", "lunedi", "lunedì" -> "LUNEDÌ";
            case "ma", "mar", "martedi", "martedì" -> "MARTEDÌ";
            case "me", "mer", "mercoledi", "mercoledì" -> "MERCOLEDÌ";
            case "gi", "gio", "giovedi", "giovedì" -> "GIOVEDÌ";
            case "ve", "ven", "venerdi", "venerdì" -> "VENERDÌ";
            case "sa", "sab", "sabato" -> "SABATO";
            default -> originalText.toUpperCase();
        };
    }

    private void sendMessage(TelegramSendMessageRequest body) {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TelegramSendMessageRequest> entity = new HttpEntity<>(body, headers);

        restTemplate.postForEntity(botUrl, entity, String.class);
    }

    private TelegramSendMessageRequest.ReplyMarkup makeKeyboard(List<String> menu, int cols) {
        List<List<TelegramSendMessageRequest.InlineKeyboardButton>> keyboard = new ArrayList<>();

        int size = menu.size();
        int fullRowNum = size / cols;
        int remainingElements = size % cols;

        for (int i = 0; i < fullRowNum; i++) {
            List<TelegramSendMessageRequest.InlineKeyboardButton> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                TelegramSendMessageRequest.InlineKeyboardButton elem =
                        TelegramSendMessageRequest.InlineKeyboardButton.builder()
                                .text(menu.get(i * cols + j))
                                .callbackData(menu.get(i * cols + j))
                                .build();
                row.add(elem);
            }
            keyboard.add(row);
        }
        List<TelegramSendMessageRequest.InlineKeyboardButton> row = new ArrayList<>();
        for (int k = 0; k < remainingElements; k++) {
            var elem = TelegramSendMessageRequest.InlineKeyboardButton.builder()
                    .text(menu.get(fullRowNum * cols + k))
                    .callbackData(menu.get(fullRowNum * cols + k))
                    .build();
            row.add(elem);
        }
        keyboard.add(row);


        return TelegramSendMessageRequest.ReplyMarkup.builder()
                .inlineKeyboard(keyboard)
                .build();
    }
}
