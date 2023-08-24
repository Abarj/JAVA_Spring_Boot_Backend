package com.example.block25SpringCloudOrderService.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;

}
