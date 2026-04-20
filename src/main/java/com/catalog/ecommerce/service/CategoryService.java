package com.catalog.ecommerce.service;

import com.catalog.ecommerce.entity.Category;
import com.catalog.ecommerce.exception.ItemNotFoundException;
import com.catalog.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(final Category category, final Long parentId) {
        if (parentId != null) {
            final Category parent = categoryRepository.findById(parentId)
                    .orElseThrow(() -> new ItemNotFoundException("Parent not found : "+ parentId));
            category.setParent(parent);
        }
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category update(final Long id, final Category updated) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Category not found : "+ id));

        category.setName(updated.getName());
        category.setDescription(updated.getDescription());

        return categoryRepository.save(category);
    }

    public void delete(final Long id) {
        categoryRepository.deleteById(id);
    }
}
