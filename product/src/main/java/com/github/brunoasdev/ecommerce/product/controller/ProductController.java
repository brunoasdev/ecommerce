package com.github.brunoasdev.ecommerce.product.controller;

import com.github.brunoasdev.ecommerce.product.exception.ProductIdNotFoundException;
import com.github.brunoasdev.ecommerce.product.model.dto.ProductDTO;
import com.github.brunoasdev.ecommerce.product.model.dto.UpdateProductQuantityDTO;
import com.github.brunoasdev.ecommerce.product.repository.ProductRepository;
import com.github.brunoasdev.ecommerce.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductRepository repository;
    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @GetMapping("{id}")
    public ProductDTO findById(@PathVariable String id) throws ProductIdNotFoundException {
        return service.findById(id);
    }

    @PutMapping("quantity/{id}")
    public ProductDTO updateQuantity(@PathVariable String id, @Valid @RequestBody UpdateProductQuantityDTO dto)
            throws ProductIdNotFoundException {
                return service.updateQuantity(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) throws ProductIdNotFoundException {
        service.delete(id);
    }
}
