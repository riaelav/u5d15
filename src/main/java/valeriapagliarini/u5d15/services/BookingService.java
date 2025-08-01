package valeriapagliarini.u5d15.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valeriapagliarini.u5d15.entities.Booking;
import valeriapagliarini.u5d15.entities.Event;
import valeriapagliarini.u5d15.entities.User;
import valeriapagliarini.u5d15.exceptions.BadRequestException;
import valeriapagliarini.u5d15.repositories.BookingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    public Booking bookEvent(Long userId, Long eventId) {
        User user = userService.findById(userId);
        Event event = eventService.getEventById(eventId);

        if (event.getAvailableSeats() <= 0) {
            throw new BadRequestException("No more seats available for this event");
        }

        Optional<Booking> existingBooking = bookingRepository.findByUserAndEvent(user, event);
        if (existingBooking.isPresent()) {
            throw new BadRequestException("You have already booked this event");
        }

        event.setAvailableSeats(event.getAvailableSeats() - 1);

        eventService.save(event);

        Booking booking = new Booking(user, event, LocalDate.now());
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUser(Long userId) {
        User user = userService.findById(userId);
        return bookingRepository.findByUser(user);
    }
}
