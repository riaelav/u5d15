package valeriapagliarini.u5d15.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valeriapagliarini.u5d15.entities.Event;
import valeriapagliarini.u5d15.entities.User;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCreator(User creator);
}
