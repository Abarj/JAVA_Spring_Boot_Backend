package com.example.block25SpringCloudOrderService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_order_line_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode; // sku = stock keeping unit -> “código de referencia” que las tiendas asignan a los productos para tener un registro interno de los niveles de existencias

    private BigDecimal price;

    private Integer quantity;
}
