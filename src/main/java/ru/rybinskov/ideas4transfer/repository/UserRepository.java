package ru.rybinskov.ideas4transfer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.rybinskov.ideas4transfer.domain.user.Role;
import ru.rybinskov.ideas4transfer.domain.user.User;

import java.util.List;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(Long id) {
        User user = jdbcTemplate.query(
                "select u.id, u.username, u.password, u.email, u.role " +
                        "from users_tbl u " +
                        "where u.id = ?",
                (u, i) -> User.builder().
                        id(u.getLong(1)).
                        name(u.getString(2)).
                        password(u.getString(3)).
                        email(u.getString(4)).
                        role(Role.valueOf(u.getString(5)))
                        .build(), id).stream().findAny().orElse(null);
        return user;
    }

    public Role findRoleByName(String name) {
        User user = jdbcTemplate.query(
                "select u.id, u.username, u.password, u.email, u.role " +
                        "from users_tbl u " +
                        "where u.username = ?",
                (u, i) -> User.builder().
                        id(u.getLong(1)).
                        name(u.getString(2)).
                        password(u.getString(3)).
                        email(u.getString(4)).
                        role(Role.valueOf(u.getString(5)))
                        .build(), name).stream().findAny().orElse(null);
        return user.getRole();
    }

    public User findFirstByName(String name) {
        User user = jdbcTemplate.query(
                "select u.id, u.username, u.password, u.email, u.role " +
                        "from users_tbl u " +
                        "where u.username = ?",
                (u, i) -> User.builder().
                        id(u.getLong(1)).
                        name(u.getString(2)).
                        password(u.getString(3)).
                        email(u.getString(4)).
                        role(Role.valueOf(u.getString(5)))
                        .build(), name).stream().findAny().orElse(null);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = jdbcTemplate.query(
                "select u.id, u.username, u.password, u.email, u.role " +
                        "from users_tbl u",
                (u, i) -> User.builder().
                        id(u.getLong(1)).
                        name(u.getString(2)).
                        password(u.getString(3)).
                        email(u.getString(4)).
                        role(Role.valueOf(u.getString(5)))
                        .build());
        return users;
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(
                "insert into users_tbl u (u.id, u.username, u.password, u.email, u.role) " +
                        "values (?, ?, ?, ?, ?)",
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getRole());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "update users_tbl u set u.username = ?, u.password = ?, u.email = ?, u.role = ?) " +
                        "values (?, ?, ?, ?) " +
                        "where u.id = ?",
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getRole(),
                user.getId());
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update("delete from users_tbl u where u.id = ?", user.getId());
    }
}
