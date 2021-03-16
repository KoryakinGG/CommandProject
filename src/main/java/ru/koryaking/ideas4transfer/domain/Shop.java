package ru.koryaking.ideas4transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project_v2", name = "shop_tbl")
public class Shop {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String abbr;

    @ManyToOne
    private Brand brand;
}
