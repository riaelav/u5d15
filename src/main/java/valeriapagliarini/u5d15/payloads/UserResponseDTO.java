package valeriapagliarini.u5d15.payloads;

import valeriapagliarini.u5d15.enums.Role;

public record UserResponseDTO(Long id, String username, String email, Role role) {
}
