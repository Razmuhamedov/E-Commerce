package com.example.magazine.dto.order;

import com.example.magazine.entity.Profile;
import com.example.magazine.type.PaymentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderCreateDto {
    private String requirement;
    private String contact;
    private String address;
    @NotNull()
    private Long profileId;
    @NotBlank(message = "Please, choose payment type!")
    private String paymentType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deliveryDate;
    @Size(min = 1)
    private List<OrderItemCreateDto> itemList;

}
