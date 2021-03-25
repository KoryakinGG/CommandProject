package ru.rybinskov.ideas4transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {

    private Long id;
    private String role;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.role = role.getRoleName();
    }

    public void updateAllFieldsWithoutId(RoleDto roleDto) {
        this.role = roleDto.getRole();
    }
}
