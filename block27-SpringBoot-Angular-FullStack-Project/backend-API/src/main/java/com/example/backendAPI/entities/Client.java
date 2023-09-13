package com.example.backendAPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "client")
@Table(name = "clients")
@AllArgsConstructor
@Builder
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "is required")
    @Size(min = 3, max = 12, message = "size must be between 3 and 12 characters")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "is required")
    private String lastName;

    @Column(name = "create_at")
    @NotNull(message = "is required")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "is required")
    @Email(message = "must have a valid email format")
    private String email;

    private String avatar;

    @NotNull(message = "is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"client", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    private List<Bill> bills;

    public Client() {
        this.bills = new ArrayList<>();
    }
}
