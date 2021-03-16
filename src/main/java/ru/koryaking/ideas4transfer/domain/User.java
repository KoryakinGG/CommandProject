package ru.koryaking.ideas4transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project_v2", name = "users_tbl")
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
//    @OneToMany
//    private Collection<? extends GrantedAuthority> authorities;


//    @OneToMany(targetEntity = LandingsAudit.class,
//            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(schema= "command_project", name = "users_landings",
//            joinColumns = @JoinColumn(name = "landings_audit"),
//            inverseJoinColumns = @JoinColumn(name = "id"))
//    private List<LandingsAudit> landingList;

//    @OneToMany(targetEntity = Landing.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL) // , mappedBy = "users_landings"

//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_landings",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "landing_id"))
//    private List<Delivery> landingList;




//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role.name()));
//        return authorities;
//    }

}
