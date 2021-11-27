package com.github.brunoasdev.ecommerce.product.mapper;

import com.github.brunoasdev.ecommerce.product.model.Product;
import com.github.brunoasdev.ecommerce.product.model.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product toModel(ProductDTO productDTO);
    ProductDTO toDTO(Product product);
}
