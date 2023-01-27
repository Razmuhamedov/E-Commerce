package com.example.magazine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = ("carts"))
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;


    @OneToOne
    @JoinColumn(name = ("profile_id"), insertable = false, updatable = false)
    private Profile profile;
    @Column(name = ("profile_id"))
    private Long profileId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<CartItem> cartItems;


}
