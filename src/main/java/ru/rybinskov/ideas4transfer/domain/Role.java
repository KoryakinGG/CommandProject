package ru.rybinskov.ideas4transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.dto.RoleDto;
import ru.rybinskov.ideas4transfer.dto.ShopDto;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "roles_tbl")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String roleName;

    public Role(RoleDto roleDto) {
        this.id = roleDto.getId();
        this.roleName = roleDto.getRole();
    }

    public void updateFields(RoleDto roleDto) {
        this.roleName = roleDto.getRole();
    }

}
