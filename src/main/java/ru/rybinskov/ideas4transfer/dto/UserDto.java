package ru.rybinskov.ideas4transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.Role;
import ru.rybinskov.ideas4transfer.domain.User;
import ru.rybinskov.ideas4transfer.domain.Warehouse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private Collection<RoleDto> roles;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private List<BrandDto> brands;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.roles = user.getRoles().stream().map(RoleDto::new).collect(Collectors.toList()); // stream
        this.brands = user.getBrands().stream().map(BrandDto::new).collect(Collectors.toList());
    }

    public void updateAllFieldsWithoutId(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.phone = userDto.getPhone();
        this.email = userDto.getEmail();
        this.fullName = userDto.getFullName();
        this.roles = userDto.getRoles();
        this.brands = userDto.getBrands();
    }
}


