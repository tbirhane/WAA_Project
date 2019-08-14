package edu.mum.cs.onlinehabeshaclothing.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String orderDate;
    private String orderStatus;
}
