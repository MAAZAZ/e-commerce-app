package com.catalog.ecommerce.controller;

import com.catalog.ecommerce.entity.Product;
import com.catalog.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product create(@RequestBody final Product product,
                          @RequestParam final Long categoryId) {
        return productService.create(product, categoryId);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable final Long id,
                          @RequestBody final Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        productService.delete(id);
    }
}
