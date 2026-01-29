package com.cg.BiteBeeFoodApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.BiteBeeFoodApplication.entity.User;
import com.cg.BiteBeeFoodApplication.repositry.UserRepository;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    /* ---------- GET ALL USERS ---------- */
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    /* ---------- GET USER BY ID ---------- */
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    /* ---------- GET USER BY EMAIL ---------- */
    public User getUserByEmail(String email) {
        return userRepository.findUserByUemail(email);
    }

    /* ---------- CHECK EMAIL EXISTS (REQUIRED FOR REGISTER) ---------- */
    public boolean existsByEmail(String email) {
        return userRepository.existsByUemail(email);
    }

    /* ---------- ADD USER ---------- */
    public void addUser(User user) {
        userRepository.save(user);
    }

    /* ---------- UPDATE USER ---------- */
    public void updateUser(User user, int id) {
        user.setU_id(id);
        userRepository.save(user);
    }

    /* ---------- DELETE USER ---------- */
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    /* ---------- LOGIN VALIDATION ---------- */
    public boolean validateLoginCredentials(String email, String password) {

        if (email == null || password == null) return false;

        email = email.trim();
        password = password.trim();

        User user = userRepository.findUserByUemail(email);

        if (user == null) return false;

        return user.getUpassword().equals(password);
    }
}
