package edu.mum.cs.onlinehabeshaclothing.controller;

import edu.mum.cs.onlinehabeshaclothing.model.FollowRelation;
import edu.mum.cs.onlinehabeshaclothing.model.Product;
import edu.mum.cs.onlinehabeshaclothing.model.ProductUtil;
import edu.mum.cs.onlinehabeshaclothing.model.User;
import edu.mum.cs.onlinehabeshaclothing.service.FollowRelationService;
import edu.mum.cs.onlinehabeshaclothing.service.ProductService;
import edu.mum.cs.onlinehabeshaclothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class FollowRelationController {

    @Autowired
    FollowRelationService followRelationService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @PostMapping("/buyer/follow")
    @ResponseBody
    public ProductUtil follow(@RequestBody ProductUtil productUtil, HttpSession session, Principal principal){

        Product product = productService.getProduct(productUtil.getId());
        User seller = product.getUser();
        FollowRelation followRelation =  new FollowRelation();
        followRelation.setUserId(seller.getId());

        User buyer = (User) session.getAttribute("buyer");
        followRelation.setTargetId(buyer.getId());
        followRelationService.saveRelation(followRelation);

        return  productUtil;
    }


}
