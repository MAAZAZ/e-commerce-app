package com.catalog.ecommerce.service;

import com.catalog.ecommerce.entity.Cart;
import com.catalog.ecommerce.entity.CartItem;
import com.catalog.ecommerce.entity.Product;
import com.catalog.ecommerce.exception.ItemNotFoundException;
import com.catalog.ecommerce.repository.CartRepository;
import com.catalog.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public Cart getCart(final Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Cart not found : " + id));
    }

    public Cart addProduct(final Long cartId, final Long productId, final int quantity) {

        final Cart cart = getCart(cartId);
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ItemNotFoundException("Product not found : " + productId));

        final Optional<CartItem> existing = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + quantity);
        } else {
            final CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);

            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    public Cart updateQuantity(final Long cartId, final Long productId, final int quantity) {
        final Cart cart = getCart(cartId);

        final CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Product not found : " + productId));

        item.setQuantity(quantity);

        return cartRepository.save(cart);
    }

    public Cart removeProduct(final Long cartId, final Long productId) {
        final Cart cart = getCart(cartId);

        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));

        return cartRepository.save(cart);
    }
}
