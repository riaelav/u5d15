package valeriapagliarini.u5d15.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valeriapagliarini.u5d15.entities.Event;
import valeriapagliarini.u5d15.entities.User;
import valeriapagliarini.u5d15.exceptions.BadRequestException;
import valeriapagliarini.u5d15.exceptions.NotFoundException;
import valeriapagliarini.u5d15.payloads.EventDTO;
import valeriapagliarini.u5d15.repositories.EventRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;

    public Event createEvent(EventDTO dto, String creatorUsername) {
        User creator = userService.findByUsername(creatorUsername);

        if (!creator.getRole().name().equals("ORGANIZER")) {
            throw new BadRequestException("Only organizers can create events");
        }

        Event event = new Event(dto.title(), dto.description(), dto.date(), dto.location(), dto.totalSeats(),
                dto.totalSeats(), creator);

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByCreator(String username) {
        User creator = userService.findByUsername(username);
        return eventRepository.findByCreator(creator);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
    }

    public Event updateEvent(Long id, EventDTO dto, String username) {
        Event existing = getEventById(id);
        if (!existing.getCreator().getUsername().equals(username)) {
            throw new BadRequestException("You can only update your own events");
        }
        existing.setTitle(dto.title());
        existing.setDescription(dto.description());
        existing.setDate(dto.date());
        existing.setLocation(dto.location());
        existing.setTotalSeats(dto.totalSeats());

        return eventRepository.save(existing);
    }


}
