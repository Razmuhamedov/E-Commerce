package com.example.magazine.dto.wishlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWishlistDto {
    private Long productId;
    private Long profileId;

}
