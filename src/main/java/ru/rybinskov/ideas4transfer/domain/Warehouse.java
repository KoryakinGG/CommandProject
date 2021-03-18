package ru.rybinskov.ideas4transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "warehouses_tbl")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String abbr;
//    @OneToMany(mappedBy = "warehouse")
//    @OneToMany
//    @JoinTable(schema= "command_project", name = "users_landings",
//            joinColumns = @JoinColumn(name = "warehouse_users"),
//            inverseJoinColumns = @JoinColumn(name = "id"))
//            joinColumns = @JoinColumn(name = "warehouse_users"))
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_brands",
            joinColumns = @JoinColumn (name = "warehouse_id"),
            inverseJoinColumns = @JoinColumn (name = "user_id")
    )
    private List<Warehouse> warehouses;
    @OneToMany(mappedBy = "warehouse")
    private List<User> users;

}
