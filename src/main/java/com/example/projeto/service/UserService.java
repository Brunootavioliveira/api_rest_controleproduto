
package com.example.projeto.service;

import com.example.projeto.model.User;
import com.example.projeto.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User updated) {
        User u = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        u.setName(updated.getName());
        u.setEmail(updated.getEmail());
        u.setPassword(updated.getPassword());
        return userRepository.save(u);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
