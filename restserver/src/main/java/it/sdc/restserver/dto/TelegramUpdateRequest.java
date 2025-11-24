package it.sdc.restserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelegramUpdateRequest(
        @JsonProperty("update_id")
        Long updateId,

        @JsonProperty("message")
        Message message
) {

    public record Message(
            @JsonProperty("message_id")
            Long messageId,

            @JsonProperty("from")
            From from,

            @JsonProperty("chat")
            Chat chat,

            @JsonProperty("date")
            Long date,

            @JsonProperty("text")
            String text
    ) {
    }

    public record From(
            @JsonProperty("id")
            Long id,

            @JsonProperty("is_bot")
            boolean isBot,

            @JsonProperty("first_name")
            String firstName,

            @JsonProperty("last_name")
            String lastName,

            @JsonProperty("username")
            String username,

            @JsonProperty("language_code")
            String languageCode
    ) {
    }

    public record Chat(
            @JsonProperty("id")
            Long id,

            @JsonProperty("first_name")
            String firstName,

            @JsonProperty("last_name")
            String lastName,

            @JsonProperty("username")
            String username,

            @JsonProperty("type")
            String type
    ) {
    }
}
