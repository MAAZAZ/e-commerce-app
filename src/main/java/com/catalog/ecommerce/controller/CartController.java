package com.catalog.ecommerce.controller;

import com.catalog.ecommerce.dto.AddProductRequest;
import com.catalog.ecommerce.dto.CartDTO;
import com.catalog.ecommerce.mapper.CartMapper;
import com.catalog.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @PostMapping
    public CartDTO createCart() {
        return cartMapper.toDTO(cartService.createCart());
    }

    @GetMapping("/{cartId}")
    public CartDTO getCart(@PathVariable final Long cartId) {
        return cartMapper.toDTO(cartService.getCart(cartId));
    }

    @PostMapping("/{cartId}/items")
    public CartDTO addProduct(@PathVariable final Long cartId,
                              @RequestBody final AddProductRequest request) {

        return cartMapper.toDTO(
                cartService.addProduct(cartId, request.getProductId(), request.getQuantity())
        );
    }

    @PutMapping("/{cartId}/items")
    public CartDTO updateQuantity(@PathVariable final Long cartId,
                                  @RequestBody final AddProductRequest request) {

        return cartMapper.toDTO(
                cartService.updateQuantity(cartId, request.getProductId(), request.getQuantity())
        );
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public CartDTO removeProduct(@PathVariable final Long cartId,
                                 @PathVariable final Long productId) {

        return cartMapper.toDTO(
                cartService.removeProduct(cartId, productId)
        );
    }
}
