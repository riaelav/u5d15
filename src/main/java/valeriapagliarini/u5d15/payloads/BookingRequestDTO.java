package valeriapagliarini.u5d15.payloads;

import jakarta.validation.constraints.NotNull;

public record BookingRequestDTO(

        @NotNull(message = "Event ID is required")
        Long eventId
) {
}
