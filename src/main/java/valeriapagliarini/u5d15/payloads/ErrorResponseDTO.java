package valeriapagliarini.u5d15.payloads;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String message,
        LocalDateTime timestamp
) {
}
