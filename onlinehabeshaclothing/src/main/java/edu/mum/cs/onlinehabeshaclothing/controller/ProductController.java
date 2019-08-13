package edu.mum.cs.onlinehabeshaclothing.controller;

import edu.mum.cs.onlinehabeshaclothing.model.Cart;
import edu.mum.cs.onlinehabeshaclothing.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProductController {
@Autowired
    IProductService productService;
    @GetMapping("/")
    public String showProduct(Model model, HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        Integer count = 0;
        if(cart != null) count = cart.getOrderLines().size();

        model.addAttribute("products", productService.findAll());
        model.addAttribute("amount", count);
        return "productList";
    }
}
