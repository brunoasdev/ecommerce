package com.github.brunoasdev.ecommerce.product.service;

import com.github.brunoasdev.ecommerce.product.exception.ProductIdNotFoundException;
import com.github.brunoasdev.ecommerce.product.mapper.ProductMapper;
import com.github.brunoasdev.ecommerce.product.model.Product;
import com.github.brunoasdev.ecommerce.product.model.dto.ProductDTO;
import com.github.brunoasdev.ecommerce.product.model.dto.UpdateProductQuantityDTO;
import com.github.brunoasdev.ecommerce.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Transactional
    public ProductDTO create(ProductDTO dto) {
        var product = productMapper.toModel(dto);
        var savedProduct = repository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public ProductDTO findById(String id) throws ProductIdNotFoundException {
        var foundProduct = repository.findById(id).orElseThrow(() -> new ProductIdNotFoundException(id));
        return productMapper.toDTO(foundProduct);
    }

    @Transactional
    public ProductDTO updateQuantity(String id, UpdateProductQuantityDTO dto) throws ProductIdNotFoundException {
        var updatedProduct = productMapper.toModel(findById(id));
        updatedProduct.setQuantity(dto.getQuantity());
        var productDTO = productMapper.toDTO(updatedProduct);
        return productDTO;
    }

    @Transactional
    public void delete(String id) throws ProductIdNotFoundException {
        verifyIfExists(id);
        repository.deleteById(id);
    }

    private Product verifyIfExists(String id) throws ProductIdNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ProductIdNotFoundException(id));
    }
}
