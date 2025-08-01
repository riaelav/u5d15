package valeriapagliarini.u5d15.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsWithListDTO(
        String message,
        LocalDateTime timestamp,
        List<String> errorsList
) {
}