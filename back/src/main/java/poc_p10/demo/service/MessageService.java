package poc_p10.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import poc_p10.demo.pojo.Message;
import poc_p10.demo.repository.MessageRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create class MessageService
 * Execute create processing
 * Use the property MessageRepository
 */

@AllArgsConstructor
@Service
public class MessageService {

    MessageRepository messageRepository;

    private final List<SseEmitter> emitters = new ArrayList<>();

    public SseEmitter connect() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        return emitter;
    }

    public void createMessage(Message message) {
        messageRepository.save(message);

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(message);
            } catch (Exception e) {
                emitters.remove(emitter);
            }
        }
    }

    public Map<String, Object> findAll() {
        List<Message> messages= messageRepository.findAll();
        Map<String,Object> response = new HashMap<>();
        response.put("messages",messages);
        return response;
    }

}
