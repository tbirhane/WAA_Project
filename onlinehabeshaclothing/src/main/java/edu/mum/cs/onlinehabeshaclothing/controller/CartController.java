package edu.mum.cs.onlinehabeshaclothing.controller;


import edu.mum.cs.onlinehabeshaclothing.controller.dto.OrderDto;
import edu.mum.cs.onlinehabeshaclothing.model.*;
import edu.mum.cs.onlinehabeshaclothing.service.ICustomerOrderService;
import edu.mum.cs.onlinehabeshaclothing.service.ProductService;
import edu.mum.cs.onlinehabeshaclothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ICustomerOrderService customerOrderService;

    //continue with this method
    @PostMapping("selectItem")
    public @ResponseBody Product selectItemToCart(@RequestBody Product product, HttpSession session){
        Cart cart = null;
       if(session.getAttribute("cart") == null){
           cart = new Cart();
       }else {
           cart = (Cart) session.getAttribute("cart");
       }

        cart.addProduct(product,1);


        session.setAttribute("cart",cart);

        return product;
    }

    //Add item to a cart
    @PostMapping(value = "addtocart", consumes = "application/json",produces = "application/json")
    public @ResponseBody
    OrderLine addToCart(@RequestBody OrderLine orderLine, HttpSession session){

        long productId = orderLine.getProduct().getId();
        int quantity = orderLine.getQuantity();

        Product product = productService.getProduct(productId);
        Cart cart = null;
        if(session.getAttribute("cart") == null){
            cart = new Cart();
        }else {
            cart = (Cart) session.getAttribute("cart");
        }
        cart.addProduct(product, quantity);
        session.setAttribute("cart",cart);

        return orderLine;
    }
    @GetMapping("cartItems")
    public String displayCartItemsList(Model model, HttpSession session){
        System.out.println("cartItems");

       Cart cart = null;
        if(session.getAttribute("cart") != null) {
            cart = (Cart) session.getAttribute("cart");
        }
        else {
            model.addAttribute("msg", "Your cart is empty!");
            cart = new Cart();
        }

        model.addAttribute("cart",cart);
        model.addAttribute("totalPrice",cart.getTotalPrice());

        return "cartItemsList";
    }

    @PostMapping(value = "updateCart", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ProductUtil updateCart(@RequestBody ProductUtil updateProduct, HttpSession session, Model model){

        long productId = updateProduct.getId();
        int quantity = updateProduct.getQuantity();

        Cart cart = null;
        if(session.getAttribute("cart") != null){
            cart = (Cart) session.getAttribute("cart");
        }
        else {
            cart = new Cart();
        }

        Product product = productService.getProduct(productId);
        if(quantity > product.getQuantity()){
            model.addAttribute("msg", "There are not enough products in our store!");
            updateProduct.setQuantity(product.getQuantity());
            cart.addProduct(product, product.getQuantity());
        }
       else {
//        cart = (Cart) session.getAttribute("cart");
            cart.addProduct(product, quantity);
        }
            updateProduct.setTotalPrice(cart.getTotalPrice());
            session.setAttribute("cart", cart);


//        System.out.println("Remove Item");
//        List<OrderLine> items = (List<OrderLine>) session.getAttribute("cart");
//        System.out.println("id = "+removedProduct.getId());
//        //remove element
//        double itemPrice =0.0;
//        for(OrderLine orderLine: items){
//            if(orderLine.getId().equals(removedProduct.getId())){
//                itemPrice = orderLine.getQuantity()* orderLine.getProduct().getPrice();
//                items.remove(orderLine);
//                break;
//            }
//        }
//        double subtotal = (double)session.getAttribute("total");
//        session.setAttribute("total", subtotal - itemPrice);
//        session.setAttribute("cart", items);
        return updateProduct;
    }


    //Remove item from cart
    @PostMapping(value = "removeItem", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ProductUtil removeItemCart(@RequestBody ProductUtil removedProduct, HttpSession session){

        long productId = removedProduct.getId();
        int quantity = removedProduct.getQuantity();
        Product product = productService.getProduct(productId);
        Cart cart = (Cart) session.getAttribute("cart");
        cart.removeProduct(product, quantity);
        removedProduct.setTotalPrice(cart.getTotalPrice());
        session.setAttribute("cart",cart);
          return removedProduct;
    }



    @GetMapping(value = "checkout")
    public String checkOut(HttpSession session, Principal principal) {
        if(session.getAttribute("buyer")==null){

            User user = userService.findByEmail(principal.getName());
            session.setAttribute("buyer", user);//current user as a Buyer
            System.out.println("*****************" + user.getId());
        }
        System.out.println("checkout");
        return "shippingForm";
    }

    @PostMapping(value = "checkout", consumes = "application/json", produces = "application/json")
    public @ResponseBody Address addShippingAddress(@RequestBody Address address, HttpSession session){
        session.setAttribute("shippingAddres",address);
        return address;
    }

    @GetMapping(value = "paymentInfo")
    public String getPayment(){
        return "checkout/paymentInfo";
    }

    @PostMapping(value = "paymentInfo")
    public @ResponseBody PaymentInfo addPayment(@RequestBody PaymentInfo paymentInfo, HttpSession session){

        session.setAttribute("paymentInfo",paymentInfo);
        Cart cart = (Cart) session.getAttribute("cart");
        User buyer = (User) session.getAttribute("buyer");
        Address address = (Address) session.getAttribute("shippingAddres");
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderDate(new Date());
        customerOrder.setOrderLineList(cart.getOrderLines());
        customerOrder.setPaymentInfo(paymentInfo);
        customerOrder.setShippingAddress(address);
        //later replace it with actual logged in user id
        customerOrder.setUser(buyer);

        session.setAttribute("customerOrder",customerOrder);
     return paymentInfo;
    }
    @PostMapping("placeOrder")
    public @ResponseBody OrderDto placeOrder(@RequestBody OrderDto orderDto, Model model, RedirectAttributes redirectAttributes, HttpSession session){
        System.out.println("placeorder");
        CustomerOrder customerOrder = (CustomerOrder) session.getAttribute("customerOrder");

        for(OrderLine line : customerOrder.getOrderLineList()) {
            Product p = productService.getProduct(line.getProduct().getId());
            p.setQuantity(p.getQuantity()-line.getQuantity());
            productService.saveProduct(p);
        }

        //for test purpose ******
            customerOrder.setOrderStatus(OrderStatus.SHIPPED);

        customerOrderService.save(customerOrder);
        System.out.println("after save");

        session.setAttribute("cart", new Cart());
        session.setAttribute("shippingAddress", new Address());
        session.setAttribute("totalPrice",0.0);
        session.invalidate();
        return orderDto;
    }
    @GetMapping("orderConfirmation")
    public String orderConfirmation(Model model, HttpSession session){
        CustomerOrder order = (CustomerOrder)session.getAttribute("customerOrder");
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("customerOrder",order);
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "orderConfirmation";
    }

    @GetMapping("orderHistory")
    public String orderHistory(Model model, HttpSession session, Principal principal){
        //to be modified when user is set :
        /***replace it with the following code.
         * ustomerOrderService.findAllOrdersByUserId(id)
         * */
        System.out.println("Order History");
        if(session.getAttribute("buyer")==null){
            System.out.println("Order History22");
            User user = userService.findByEmail(principal.getName());
            session.setAttribute("buyer", user);//current user as a Buyer
            System.out.println("Order history *****************" + user.getId());
        }
        User buyer = (User) session.getAttribute("buyer");
        System.out.println("IDD == "+buyer.getId());
        model.addAttribute("orderHistory",customerOrderService.findAllOrdersByUserId(buyer.getId()));
        return "maintainOrder";
    }

    @PostMapping("cancelOrder")
    public @ResponseBody
    OrderDto cancelOrder(@RequestBody OrderDto orderDto){
        CustomerOrder customerOrder = customerOrderService.findById(orderDto.getId());

        if(customerOrder.getOrderStatus().equals(OrderStatus.SHIPPED)){
            orderDto.setOrderDate("Product is already shipped!");
            return orderDto;
        }
        List<OrderLine> orderLineList = customerOrder.getOrderLineList();
        //loop through the orderlines and return products to store

        for(OrderLine line: orderLineList){
            Product returnedProduct = line.getProduct();
            int quantity = line.getQuantity();
            Product product = productService.getProduct(returnedProduct.getId());
            if(product != null){
                product.setQuantity(product.getQuantity()+quantity);
            }else {

                product = returnedProduct;
            }

            productService.saveProduct(product);
        }

        customerOrderService.delete(customerOrder);
        return orderDto;
    }

}
