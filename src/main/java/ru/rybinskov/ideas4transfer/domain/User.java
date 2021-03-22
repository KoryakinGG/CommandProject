package ru.rybinskov.ideas4transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.UserDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "users_tbl")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String fullName;
    @Column
    private String email;
    @Column
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

//    @ManyToOne
    @OneToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

     @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  //  cascade = {  CascadeType.PERSIST, CascadeType.MERGE}
     @JoinTable(
        name = "users_brands_tbl",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "brand_id") })
    private List<Brand> brands;

    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.phone = userDto.getPhone();
        this.email = userDto.getEmail();
        this.fullName = userDto.getFullName();
        this.role = userDto.getRole();
        this.warehouse = userDto.getWarehouse();
        this.brands = userDto.getBrands().stream().map(Brand::new).collect(Collectors.toList());
    }
}
