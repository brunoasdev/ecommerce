package com.github.brunoasdev.ecommerce.product.controller;

import com.github.brunoasdev.ecommerce.product.model.Product;
import com.github.brunoasdev.ecommerce.product.model.dto.AddProductDTO;
import com.github.brunoasdev.ecommerce.product.model.dto.UpdateProductQuantityDTO;
import com.github.brunoasdev.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepository repository;

    @Autowired
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> create(@Valid @RequestBody AddProductDTO dto) {
        var product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
        return ResponseEntity.ok().body(repository.save(product));
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        return repository.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("quantity/{id}")
    @Transactional
    public ResponseEntity<Product> updateQuantity(@PathVariable String id, @Valid @RequestBody UpdateProductQuantityDTO dto) {
        return repository.findById(id)
                .map(existingProduct -> {
                existingProduct.setQuantity(dto.getQuantity());
                    return repository.save(existingProduct);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return repository.findById(id)
                .map(existingProduct -> {
                    repository.delete(existingProduct);
                    return(ResponseEntity.ok().<Void>build());
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
