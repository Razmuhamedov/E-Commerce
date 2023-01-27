package com.example.magazine.dto.rate;

import com.example.magazine.entity.Product;
import com.example.magazine.entity.Profile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateDto {
    private Long id;
    private Double rate;
    private Profile profile;
    private Product product;
}
