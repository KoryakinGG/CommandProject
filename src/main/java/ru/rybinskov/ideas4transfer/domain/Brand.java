package ru.rybinskov.ideas4transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.dto.BrandDto;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "brands_tbl")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String abbr;
//
//    @ManyToMany
//    @JoinTable(
//            name = "users_brands_tbl",
//            joinColumns = { @JoinColumn(name = "brand_id") },
//            inverseJoinColumns = { @JoinColumn(name = "user_id") })
//    private List<User> users;


    @OneToMany(mappedBy = "brand")
    private List<Shop> shops;

    public Brand(BrandDto brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.abbr = brand.getAbbr();
    }

}
