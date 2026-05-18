package poc_p10.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import poc_p10.demo.pojo.Message;
import poc_p10.demo.repository.MessageRepository;

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

    public void createMessage(Message message) {
        messageRepository.save(message);
    }

    public Map<String, Object> findAll() {
        List<Message> messages= messageRepository.findAll();
        Map<String,Object> response = new HashMap<>();
        response.put("messages",messages);
        return response;
    }
}
