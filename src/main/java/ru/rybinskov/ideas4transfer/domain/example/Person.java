package ru.rybinskov.ideas4transfer.domain.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "command_project", name = "persons")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
//    @Column
    private Gender gender;

    @OneToMany(targetEntity = Hobby.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @CollectionTable(schema = "command_project",
            name = "persons_hobbies",
            joinColumns = {@JoinColumn(name = "persons_id",
                    referencedColumnName = "id")})
    private List<Hobby> hobbies;

}
