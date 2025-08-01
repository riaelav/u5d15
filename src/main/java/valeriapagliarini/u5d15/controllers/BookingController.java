package valeriapagliarini.u5d15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import valeriapagliarini.u5d15.entities.Booking;
import valeriapagliarini.u5d15.entities.User;
import valeriapagliarini.u5d15.services.BookingService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking bookEvent(@PathVariable Long eventId, @AuthenticationPrincipal User currentUser) {
        return bookingService.bookEvent(currentUser.getId(), eventId);
    }
    
    @GetMapping("/me")
    public List<Booking> getMyBookings(@AuthenticationPrincipal User currentUser) {
        return bookingService.getBookingsByUser(currentUser.getId());
    }


}
