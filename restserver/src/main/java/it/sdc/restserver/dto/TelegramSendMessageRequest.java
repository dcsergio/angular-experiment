package it.sdc.restserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TelegramSendMessageRequest(
        @JsonProperty("chat_id")
        String chatId,

        @JsonProperty("text")
        String text,

        @JsonProperty("reply_markup")
        ReplyMarkup replyMarkup,

        @JsonProperty("method")
        String method
) {

    public record ReplyMarkup(
            @JsonProperty("inline_keyboard")
            InlineKeyboardButton[][] inlineKeyboard
    ) {
    }

    public record InlineKeyboardButton(
            @JsonProperty("text")
            String text,

            @JsonProperty("callback_data")
            String callbackData
    ) {
    }
}