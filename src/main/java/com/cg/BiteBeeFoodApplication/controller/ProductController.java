package com.cg.BiteBeeFoodApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cg.BiteBeeFoodApplication.entity.Product;
import com.cg.BiteBeeFoodApplication.service.ProductServices;

@Controller
public class ProductController {

    @Autowired
    private ProductServices productServices;

    /* ---------- ADD PRODUCT PAGE ---------- */
    @GetMapping("/addProduct")
    public String addProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "Add_Product";
    }

    /* ---------- SAVE PRODUCT ---------- */
    @PostMapping("/addingProduct")
    public String addProduct(@ModelAttribute Product product) {
        productServices.addProduct(product);
        return "redirect:/admin/services";
    }

    /* ---------- UPDATE PRODUCT PAGE ---------- */
    @GetMapping("/updateProduct/{productId}")
    public String updateProductPage(
            @PathVariable int productId,
            Model model) {

        Product product = productServices.getProduct(productId);
        model.addAttribute("product", product);
        return "Update_Product";
    }

    /* ---------- UPDATE PRODUCT ---------- */
    @PostMapping("/updatingProduct/{productId}")
    public String updateProduct(
            @PathVariable int productId,
            @ModelAttribute Product product) {

        productServices.updateProduct(product, productId);
        return "redirect:/admin/services";
    }

    /* ---------- DELETE PRODUCT ---------- */
    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productServices.deleteProduct(productId);
        return "redirect:/admin/services";
    }
}
