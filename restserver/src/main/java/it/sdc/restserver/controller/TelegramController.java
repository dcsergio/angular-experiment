package it.sdc.restserver.controller;


import it.sdc.restserver.dto.TelegramUpdateRequest;
import it.sdc.restserver.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "telegram")
@Slf4j
@RequiredArgsConstructor
public class TelegramController {

    private final TelegramService telegramService;

    @PostMapping(value = "useless_bot")
    public ResponseEntity<Void> onUpdate(@RequestBody TelegramUpdateRequest update) {
        telegramService.sendResponse(update);
        return ResponseEntity.ok().build();
    }

}
