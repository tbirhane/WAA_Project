package edu.mum.cs.onlinehabeshaclothing.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductUtil {
    private Long id;
    private int quantity;
    private double totalPrice;
}
