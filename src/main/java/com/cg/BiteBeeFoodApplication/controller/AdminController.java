package com.cg.BiteBeeFoodApplication.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cg.BiteBeeFoodApplication.entity.Admin;
import com.cg.BiteBeeFoodApplication.entity.User;
import com.cg.BiteBeeFoodApplication.loginCredentials.AdminLogin;
import com.cg.BiteBeeFoodApplication.loginCredentials.UserLogin;
import com.cg.BiteBeeFoodApplication.service.*;

@Controller
public class AdminController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private AdminServices adminServices;

    @Autowired
    private ProductServices productServices;

    @Autowired
    private OrderServices orderServices;

    /* ================= ADMIN LOGIN ================= */

    @PostMapping("/adminLogin")
    public String adminLogin(@ModelAttribute AdminLogin login,
                             Model model,
                             HttpSession session) {

        if (adminServices.validateAdminCredentials(
                login.getEmail(), login.getPassword())) {

            session.setAttribute("admin", login.getEmail());
            return "redirect:/admin/services";
        }

        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }

    /* ================= USER LOGIN ================= */

    @PostMapping("/userLogin")
    public String userLogin(@ModelAttribute UserLogin login,
                            Model model,
                            HttpSession session) {

        if (userServices.validateLoginCredentials(
                login.getUserEmail(), login.getUserPassword())) {

            User user = userServices.getUserByEmail(login.getUserEmail());
            session.setAttribute("loggedUser", user);
            return "redirect:/products";
        }

        model.addAttribute("error2", "Invalid email or password");
        return "Login";
    }

    /* ================= ADMIN DASHBOARD ================= */

    @GetMapping("/admin/services")
    public String adminDashboard(Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/Login";
        }

        model.addAttribute("users", userServices.getAllUser());
        model.addAttribute("admins", adminServices.getAll());
        model.addAttribute("products", productServices.getAllProducts());
        model.addAttribute("orders", orderServices.getOrders());

        return "Admin_Page";
    }

    /* ================= ADD ADMIN ================= */

    @GetMapping("/addAdmin")
    public String addAdminPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "Add_Admin";
    }

    @PostMapping("/addingAdmin")
    public String saveAdmin(@ModelAttribute Admin admin) {
        adminServices.addAdmin(admin);
        return "redirect:/admin/services";
    }

    /* ================= UPDATE ADMIN (FIXED) ================= */

    @GetMapping("/updateAdmin/{id}")
    public String updateAdminPage(@PathVariable int id, Model model) {

        Admin admin = adminServices.getAdmin(id);
        model.addAttribute("admin", admin);

        return "Update_Admin";
    }

    @PostMapping("/updatingAdmin/{id}")
    public String updateAdmin(@PathVariable int id,
                              @ModelAttribute Admin admin) {

        adminServices.update(admin, id);
        return "redirect:/admin/services";
    }

    /* ================= DELETE ADMIN ================= */

    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable int id) {
        adminServices.delete(id);
        return "redirect:/admin/services";
    }
}





//package com.cg.BiteBeeFoodApplication.controller;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.cg.BiteBeeFoodApplication.count.Logic;
//import com.cg.BiteBeeFoodApplication.entity.Admin;
//import com.cg.BiteBeeFoodApplication.entity.Orders;
//import com.cg.BiteBeeFoodApplication.entity.Product;
//import com.cg.BiteBeeFoodApplication.entity.User;
//import com.cg.BiteBeeFoodApplication.loginCredentials.AdminLogin;
//import com.cg.BiteBeeFoodApplication.loginCredentials.UserLogin;
//import com.cg.BiteBeeFoodApplication.service.AdminServices;
//import com.cg.BiteBeeFoodApplication.service.OrderServices;
//import com.cg.BiteBeeFoodApplication.service.ProductServices;
//import com.cg.BiteBeeFoodApplication.service.UserServices;
//
//@Controller
//public class AdminController {
//
//    @Autowired
//    private UserServices services;
//
//    @Autowired
//    private AdminServices adminServices;
//
//    @Autowired
//    private ProductServices productServices;
//
//    @Autowired
//    private OrderServices orderServices;
//
//    /* =============================
//       LOGIN PAGE (NO URL CHANGE)
//       ============================= */
//
////    @GetMapping("/")
////    public String home() {
////        return "redirect:/Login";
////    }
//
//    @GetMapping("/Login")
//    public String loginPage(Model model) {
//        model.addAttribute("adminLogin", new AdminLogin());
//        model.addAttribute("userLogin", new UserLogin());
//        return "Login";
//    }
//
//    /* =============================
//       ADMIN LOGIN
//       ============================= */
//
//    @PostMapping("/adminLogin")
//    public String adminLogin(@ModelAttribute("adminLogin") AdminLogin login,
//                             Model model) {
//
//        String email = login.getEmail().trim().toLowerCase();
//        String password = login.getPassword().trim();
//
//        if (adminServices.validateAdminCredentials(email, password)) {
//            return "redirect:/admin/services";
//        } else {
//            model.addAttribute("error", "Invalid email or password");
//            return "Login";
//        }
//    }
//
//    /* =============================
//       USER LOGIN
//       ============================= */
//
//    @PostMapping("/userLogin")
//    public String userLogin(@ModelAttribute("userLogin") UserLogin login,
//                            Model model) {
//
//        String email = login.getUserEmail().trim().toLowerCase();
//        String password = login.getUserPassword().trim();
//
//        if (services.validateLoginCredentials(email, password)) {
//
//            User user = services.getUserByEmail(email);
//            List<Orders> orders = orderServices.getOrdersForUser(user);
//
//            model.addAttribute("orders", orders);
//            model.addAttribute("name", user.getUname());
//            model.addAttribute("user", user);
//
//            return "BuyProduct";
//        } else {
//            model.addAttribute("error2", "Invalid email or password");
//            return "Login";
//        }
//    }
//
//    /* =============================
//       ADMIN DASHBOARD
//       ============================= */
//
//    @GetMapping("/admin/services")
//    public String adminServices(Model model) {
//
//        model.addAttribute("users", services.getAllUser());
//        model.addAttribute("admins", adminServices.getAll());
//        model.addAttribute("products", productServices.getAllProducts());
//        model.addAttribute("orders", orderServices.getOrders());
//
//        return "Admin_Page";
//    }
//
//    /* =============================
//       ADMIN CRUD
//       ============================= */
//
//    @GetMapping("/addAdmin")
//    public String addAdminPage() {
//        return "Add_Admin";
//    }
//
//    @PostMapping("/addingAdmin")
//    public String addAdmin(@ModelAttribute Admin admin) {
//        adminServices.addAdmin(admin);
//        return "redirect:/admin/services";
//    }
//
//    @GetMapping("/updateAdmin/{adminId}")
//    public String updateAdmin(@PathVariable int adminId, Model model) {
//        model.addAttribute("admin", adminServices.getAdmin(adminId));
//        return "Update_Admin";
//    }
//
//    @GetMapping("/updatingAdmin/{id}")
//    public String updateAdminSave(@ModelAttribute Admin admin,
//                                  @PathVariable int id) {
//        adminServices.update(admin, id);
//        return "redirect:/admin/services";
//    }
//
//    @GetMapping("/deleteAdmin/{id}")
//    public String deleteAdmin(@PathVariable int id) {
//        adminServices.delete(id);
//        return "redirect:/admin/services";
//    }
//
//    /* =============================
//       USER CRUD
//       ============================= */
//
//    @GetMapping("/addUser")
//    public String addUserPage() {
//        return "Add_User";
//    }
//
//    @GetMapping("/updateUser/{userId}")
//    public String updateUser(@PathVariable int userId, Model model) {
//        model.addAttribute("user", services.getUser(userId));
//        return "Update_User";
//    }
//
//    /* =============================
//       PRODUCT CRUD
//       ============================= */
//
//    @GetMapping("/addProduct")
//    public String addProductPage() {
//        return "Add_Product";
//    }
//
//    @GetMapping("/updateProduct/{productId}")
//    public String updateProduct(@PathVariable int productId, Model model) {
//        model.addAttribute("product", productServices.getProduct(productId));
//        return "Update_Product";
//    }
//
//    @PostMapping("/product/search")
//    public String searchProduct(@RequestParam("productName") String name,
//                                @RequestParam("userId") int userId,
//                                Model model) {
//
//        User user = services.getUser(userId);
//        Product product = productServices.getProductByName(name);
//
//        List<Orders> orders = orderServices.getOrdersForUser(user);
//        model.addAttribute("orders", orders);
//        model.addAttribute("user", user);
//
//        if (product == null) {
//            model.addAttribute("message", "SORRY...! Product Unavailable");
//        } else {
//            model.addAttribute("product", product);
//        }
//
//        return "BuyProduct";
//    }
//
//    /* =============================
//       ORDER
//       ============================= */
//
//    @PostMapping("/product/order")
//    public String placeOrder(@ModelAttribute Orders order,
//                             @RequestParam("userId") int userId,
//                             Model model) {
//
//        User user = services.getUser(userId);
//
//        double totalAmount =
//                Logic.countTotal(order.getoPrice(), order.getoQuantity());
//
//        order.setTotalAmount(totalAmount);
//        order.setUser(user);
//        order.setOrderDate(new Date());
//
//        orderServices.saveOrder(order);
//
//        model.addAttribute("amount", totalAmount);
//        return "Order_success";
//    }
//
//    @GetMapping("/product/back/{userId}")
//    public String back(@PathVariable int userId, Model model) {
//
//        User user = services.getUser(userId);
//        List<Orders> orders = orderServices.getOrdersForUser(user);
//
//        model.addAttribute("orders", orders);
//        model.addAttribute("user", user);
//
//        return "BuyProduct";
//    }
//}
//
