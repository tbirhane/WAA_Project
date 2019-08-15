package edu.mum.cs.onlinehabeshaclothing.controller;


import edu.mum.cs.onlinehabeshaclothing.model.*;
import edu.mum.cs.onlinehabeshaclothing.service.FollowRelationService;

import edu.mum.cs.onlinehabeshaclothing.model.Cart;
import edu.mum.cs.onlinehabeshaclothing.model.Product;

import edu.mum.cs.onlinehabeshaclothing.service.ProductService;
import edu.mum.cs.onlinehabeshaclothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    FollowRelationService followRelationService;
    @GetMapping("/productsList")
    public String showProduct(Model model, HttpSession session){


        Cart cart = (Cart) session.getAttribute("cart");
        Integer count = 0;
        if(cart != null) count = cart.getOrderLines().size();
        List<Product> productList = productService.getProducts();
        List<Product> approvedProducts = new ArrayList<>();
        for (Product product : productList){
            if(product.isApprove()){
                approvedProducts.add(product);
            }
        }
        model.addAttribute("products", approvedProducts);
        model.addAttribute("amount", count);
        return "productList";
    }

    @RequestMapping("/detail/{id}")
    public String product(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "detail";
    }

    @PostMapping("/buyer/products/detail")
    @ResponseBody
    public ProductUtil getProduct(@RequestBody ProductUtil productUtil, HttpSession session){
        session.setAttribute("product", productService.getProduct(productUtil.getId()));
        return productUtil;
    }

    @GetMapping("/buyer/products/review")
    public String reviewProduct(Model model, HttpSession session, Principal principal){
        if(session.getAttribute("buyer")==null){
            User user = userService.findByEmail(principal.getName());
            session.setAttribute("buyer", user);//current user as a Buyer
        }
        Product product = (Product) session.getAttribute("product");
        model.addAttribute("product", product);
        User buyer = (User) session.getAttribute("buyer");
        FollowRelation followRelation = followRelationService.findRelation(product.getUser().getId(),
                buyer.getId());
        if(followRelation !=null){
            model.addAttribute("followStatus", "following");
        }
        else {
            model.addAttribute("followStatus", "follow");
        }
        return "detail";
    }
    @RequestMapping(value = "/seller/products", method = RequestMethod.GET)
    public String productsList(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

    @RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
    @ResponseBody
    public String saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return product.getId().toString();
    }

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

//    @RequestMapping("/")
//    public String UploadPage(Model model) {
//        return "uploadview";
//    }

    @RequestMapping("/upload")
    public String upload(Model model, @RequestParam("files") MultipartFile file) {
        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

        try {
            Files.write(fileNameAndPath, file.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getOriginalFilename());
        model.addAttribute("msg", file.getOriginalFilename());
        //ResourcesPlugin.getWorkspace().getRoot().getProjects()
        return "uploadstatusview";
    }

    @GetMapping("/admin/products/unapprovedProducts")
    public String getProductsToApprove(Model model){
        List<Product> productList = productService.getProducts();
        List<Product> approvedProducts = new ArrayList<>();
        for (Product product : productList){
            if(!product.isApprove()){
                approvedProducts.add(product);
            }
        }
        model.addAttribute("products", approvedProducts);
        return "/approveproducts";

    }

    @PostMapping("/products/approve")
    @ResponseBody
    public Product saveApproved(@RequestBody Product product){
        productService.saveProduct(product);

        return product;
    }
    @PostMapping("/products/reject")
    @ResponseBody
    public Product rejectApproved(@RequestBody Product product){
        productService.deleteProduct(product.getId());

        return product;
    }
//    @GetMapping("/approve")
//    public String approve(Model model){
//
//        return "/admin/approveproducts";
//
//    }
}
