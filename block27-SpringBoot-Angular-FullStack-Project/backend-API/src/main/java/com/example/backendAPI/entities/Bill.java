package com.example.backendAPI.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "bill")
@Table(name = "bills")
@AllArgsConstructor
@Builder
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String observations;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"bills", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ItemBill> items;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Bill() {
        items = new ArrayList<>();
    }

    public Double getTotal() {
        Double total = 0.00;

        for(ItemBill item: items) {
            total += item.getInvoice();
        }

        return total;
    }
}
