package it.sdc.restserver.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.sdc.restserver.dto.Update;
import it.sdc.restserver.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "telegram")
@Slf4j
@RequiredArgsConstructor
public class TelegramController {

    private final TelegramService telegramService;
    private final ObjectMapper objectMapper;

    @PostMapping(value = "useless_bot")
    public ResponseEntity<Void> onUpdate(@RequestBody Update update) throws JsonProcessingException {
        log.info("""
                json:
                {}
                """, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(update));
        telegramService.sendResponse(update);
        return ResponseEntity.ok().build();
    }

}
