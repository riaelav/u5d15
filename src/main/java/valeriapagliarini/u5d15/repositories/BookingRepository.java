package valeriapagliarini.u5d15.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valeriapagliarini.u5d15.entities.Booking;
import valeriapagliarini.u5d15.entities.Event;
import valeriapagliarini.u5d15.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByEvent(Event event);

    Optional<Booking> findByUserAndEvent(User user, Event event);
}
