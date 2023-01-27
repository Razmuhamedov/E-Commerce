package com.example.magazine.dto.product;

import com.example.magazine.dto.image.ImageDto;
import com.example.magazine.entity.Image;
import com.example.magazine.entity.ProductImage;
import com.example.magazine.type.ProductStatus;
import com.example.magazine.type.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double rate;
    private ProductType productType;
    private ProductStatus status;
    private List<ImageDto> imageList;
}
