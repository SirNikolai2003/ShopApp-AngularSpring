package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    @JsonProperty("user_id")
    @Min(value = 1, message = "User's ID must be > 0")
    private Long userId;

    @JsonProperty("fullname")
    private String fullname;

    private String email;
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phone_number;

    private String address;
    private String note;
    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be >=0")
    private Float totalMoney;

    @JsonProperty("shipping_method")
    private Float shippingMethod;

    @JsonProperty("payment_method")
    private Float paymentMethod;


}
