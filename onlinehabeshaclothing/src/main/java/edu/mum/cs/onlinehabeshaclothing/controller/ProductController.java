package edu.mum.cs.onlinehabeshaclothing.controller;

import edu.mum.cs.onlinehabeshaclothing.model.Cart;
import edu.mum.cs.onlinehabeshaclothing.model.Product;
import edu.mum.cs.onlinehabeshaclothing.service.IProductService;
import edu.mum.cs.onlinehabeshaclothing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductController {
@Autowired
ProductService productService;
    @GetMapping("/productsList")
    public String showProduct(Model model, HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        Integer count = 0;
        if(cart != null) count = cart.getOrderLines().size();

        model.addAttribute("products", productService.getProducts());
        model.addAttribute("amount", count);
        return "productList";
    }

    @RequestMapping("/detail/{id}")
    public String product(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "detail";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
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

    @RequestMapping("/")
    public String UploadPage(Model model) {
        return "uploadview";
    }

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
}
