package com.github.brunoasdev.ecommerce.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String id;

    @NotEmpty(message = "Name is mandatory.")
    @Size(min = 3, max = 30, message = "Minimum {min} and maximum {max} characters.")
    private String name;

    @NotNull(message = "Price is mandatory.")
    private BigDecimal price;

    @NotNull(message = "Quantity is mandatory.")
    private Integer quantity;

}
