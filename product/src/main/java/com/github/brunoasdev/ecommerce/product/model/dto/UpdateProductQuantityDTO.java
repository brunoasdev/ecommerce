package com.github.brunoasdev.ecommerce.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductQuantityDTO {

    @NotNull(message = "Quantity is mandatory.")
    private Integer quantity;

}
