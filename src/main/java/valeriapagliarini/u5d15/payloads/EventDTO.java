package valeriapagliarini.u5d15.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventDTO(

        @NotBlank(message = "Title is required")
        String title,

        String description,

        @NotNull(message = "Date is required")
        LocalDateTime date,

        @NotBlank(message = "Location is required")
        String location,

        @NotNull(message = "Total seats are required")
        int totalSeats
) {
}
