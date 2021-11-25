package com.github.brunoasdev.ecommerce.product.repository;

import com.github.brunoasdev.ecommerce.product.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
}
