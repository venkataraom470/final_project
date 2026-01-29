package com.cg.BiteBeeFoodApplication.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {

    /* ================= ROOT ================= */
    @GetMapping("/")
    public String root() {
        return "redirect:/Login";
    }

    /* ================= LOGIN ================= */
    @GetMapping("/Login")
    public String login() {
        return "Login";
    }

    /* ================= HOME ================= */
    @GetMapping("/home")
    public String home() {
        return "Home";
    }

    /* ================= PRODUCTS (LOGIN REQUIRED) ================= */
    @GetMapping("/products")
    public String products(HttpSession session) {

        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/Login";
        }
        return "Products";
    }

    /* ================= BUY PRODUCT (LOGIN REQUIRED) ================= */
    @GetMapping("/buyProduct")
    public String buyProduct(
            @RequestParam String pname,
            @RequestParam double pprice,
            Model model,
            HttpSession session) {

        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/Login";
        }

        model.addAttribute("pname", pname);
        model.addAttribute("pprice", pprice);

        return "BuyProduct";
    }

    /* ================= ABOUT ================= */
    @GetMapping("/about")
    public String about() {
        return "About";
    }

    /* ================= LOCATION ================= */
    @GetMapping("/location")
    public String location() {
        return "Locate_us";
    }

    /* ================= LOGOUT ================= */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/Login";
    }
}
