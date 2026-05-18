package poc_p10.demo.pojo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Build Class message
 * Set up properties (id, content, response and user_id)
 * Implement constructor
 * @Getter and @Setter allows the implementation of getter and setter
 */

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column
    private String response;

    @Column(nullable = true)
    private Long user_id;

    public Message(Long id, String content, String response, Long user_id) {
        this.id = id;
        this.content = content;
        this.response = response;
        this.user_id = user_id;
    }

    public Message() {
    }
}
