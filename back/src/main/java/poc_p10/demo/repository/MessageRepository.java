package poc_p10.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poc_p10.demo.pojo.Message;

/**
 * Build MessageRepository
 * extends CrudRepository from tools Jpa
 * @Params Long id
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
