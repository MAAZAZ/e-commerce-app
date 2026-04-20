package com.catalog.ecommerce.mapper;

import com.catalog.ecommerce.dto.CartDTO;
import com.catalog.ecommerce.dto.CartItemDTO;
import com.catalog.ecommerce.entity.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    public CartDTO toDTO(final Cart cart) {
        final CartDTO dto = new CartDTO();

        dto.setId(cart.getId());

        final List<CartItemDTO> items = cart.getItems().stream().map(item -> {
            final CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setProductId(item.getProduct().getId());
            cartItemDTO.setProductName(item.getProduct().getName());
            cartItemDTO.setPrice(item.getProduct().getPrice());
            cartItemDTO.setQuantity(item.getQuantity());
            return cartItemDTO;
        }).toList();

        dto.setItems(items);

        final double total = items.stream()
                .mapToDouble(cartItemDTO -> cartItemDTO.getPrice() * cartItemDTO.getQuantity())
                .sum();

        dto.setTotal(total);

        return dto;
    }
}
