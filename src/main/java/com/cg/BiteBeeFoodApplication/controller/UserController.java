package com.cg.BiteBeeFoodApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cg.BiteBeeFoodApplication.entity.User;
import com.cg.BiteBeeFoodApplication.service.UserServices;

@Controller
public class UserController {

    @Autowired
    private UserServices userServices;

    /* ================= ADMIN SIDE ================= */

    // ADD USER PAGE (ADMIN)
    @GetMapping("/addUser")
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "Add_User";
    }

    // SAVE USER (ADMIN)
    @PostMapping("/addingUser")
    public String addUser(@ModelAttribute User user) {
        userServices.addUser(user);
        return "redirect:/admin/services";
    }

    // UPDATE USER PAGE
    @GetMapping("/updateUser/{id}")
    public String updateUserPage(@PathVariable int id, Model model) {
        User user = userServices.getUser(id);
        model.addAttribute("user", user);
        return "Update_User";
    }

    // UPDATE USER
    @PostMapping("/updatingUser/{id}")
    public String updateUser(@PathVariable int id,
                             @ModelAttribute User user) {
        userServices.updateUser(user, id);
        return "redirect:/admin/services";
    }

    // DELETE USER
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userServices.deleteUser(id);
        return "redirect:/admin/services";
    }

    /* ================= USER REGISTRATION ================= */

    // REGISTER PAGE (PUBLIC)
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // SAVE REGISTERED USER
    @PostMapping("/saveUser")
    public String saveRegisteredUser(@ModelAttribute User user, Model model) {

        // OPTIONAL: prevent duplicate email
        if (userServices.existsByEmail(user.getUemail())) {
            model.addAttribute("error", "Email already exists");
            model.addAttribute("user", user);
            return "register";
        }

        userServices.addUser(user);
        return "redirect:/Login";
    }
}
