package com.example.magazine.dto.rate;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RateCreateDto {
    @Size(min = 0, max = 10, message = "Please, input between 0 and 10")
    private Double rate;
    @NotNull
    private Long profileId;
    @NotNull(message = "Please, choose product for rating")
    private Long productId;
}
