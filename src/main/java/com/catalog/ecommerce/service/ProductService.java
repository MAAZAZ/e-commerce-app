package com.catalog.ecommerce.service;


import com.catalog.ecommerce.entity.Category;
import com.catalog.ecommerce.entity.Product;
import com.catalog.ecommerce.exception.ItemNotFoundException;
import com.catalog.ecommerce.repository.CategoryRepository;
import com.catalog.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product create(final Product product, final Long categoryId) {
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ItemNotFoundException("Category not found : "+ categoryId));

        product.setCategory(category);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product update(final Long id, final Product updated) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product not found : " + id));

        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        product.setStock(updated.getStock());

        return productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }
}
