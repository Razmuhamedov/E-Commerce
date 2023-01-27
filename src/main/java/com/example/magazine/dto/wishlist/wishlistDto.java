package com.example.magazine.dto.wishlist;

import com.example.magazine.dto.product.ProductDto;
import com.example.magazine.entity.Profile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class wishlistDto {
    private Long id;
    private  Integer quantity;
    private List<ProductDto> productDtoList;
    private Profile profile;
}
