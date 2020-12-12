package ru.rybinskov.ideas4transfer.service.user_service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.rybinskov.ideas4transfer.dao.UserDao;
import ru.rybinskov.ideas4transfer.domain.user.User;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User auth(String username, String password) {
        if (username == null || username.isEmpty()) {
            System.out.println("You are not authenticated");
            return null;
        }
        User user = userDao.findFirstByName(username);
        if (user == null) {
            System.out.println("You are not authenticated");
            return null;
        }
        if (!Objects.equals(password, user.getPassword())) {
            System.out.println("You are not authenticated");
            return null;
        }
        System.out.println("You are authenticated");
        return user;
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User findByName(String name) {
        return userDao.findFirstByName(name);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findFirstByName(username);
    }
}
