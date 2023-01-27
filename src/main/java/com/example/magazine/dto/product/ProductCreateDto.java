package com.example.magazine.dto.product;

import com.example.magazine.type.ProductStatus;
import com.example.magazine.type.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductCreateDto {
    @NotBlank(message = "Name not should be null or empty!")
    private String name;
    @NotBlank(message = "Description not should be null or empty!")
    private String description;
    @NotNull(message = "Price not should be null!")
    private Double price;
    @NotBlank(message = "Please, choose product type!")
    private ProductType productType;
    private Double rate;
}
