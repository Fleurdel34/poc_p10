package poc_p10.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import poc_p10.demo.pojo.Message;
import poc_p10.demo.service.MessageService;


import java.util.Map;

/**
 * Create class MessageController
 * Use the property MessageService
 */

@AllArgsConstructor
@RestController
@RequestMapping({"/api/message"})
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createMessage(@RequestBody Message message) throws RuntimeException {
        this.messageService.createMessage(message);
        Map<String, String> response = Map.of(
                "message", "Message send with success"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllMessages() {
        Map<String, Object> messages = this.messageService.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/stream")
    public SseEmitter stream() {
        return messageService.connect();
    }
}
