package edu.mum.cs.onlinehabeshaclothing.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
//@RequiredArgsConstructor
public class Cart {
    private List<OrderLine> orderLines;
    private double totalPrice;

    public Cart(){
        orderLines = new ArrayList<>();
    }


    public OrderLine addProduct(Product product, int quantity){
        OrderLine orderLine = null;
        for(OrderLine l:orderLines){
           if(l.getProduct().getId()  == product.getId()){
               orderLine = l;
               break;
           }
        }
        if(orderLine == null){
            orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantity(quantity);
            orderLines.add(orderLine);
        }
        else {
            orderLine.setQuantity(quantity);
        }
        return orderLine;
    }

    public double  getTotalPrice(){
        double total = 0.0;
        for (OrderLine lines: orderLines){
            total += lines.getProduct().getPrice()*lines.getQuantity();
        }
        return total;
    }

    public void removeProduct(Product product, int quantity) {
        OrderLine orderLine = null;
        for(OrderLine l:orderLines){
            if(l.getProduct().getId()  == product.getId()){
                orderLine = l;
                break;
            }
        }
        if(orderLine != null) {
            orderLine.setQuantity(orderLine.getQuantity() - quantity);
            if(orderLine.getQuantity() < 1)
                orderLines.remove(orderLine);
        }
    }
}
