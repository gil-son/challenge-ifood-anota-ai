package com.challenge.domain.product;

import com.challenge.domain.category.Category;

public record ProductDTO(String title, String description, String ownerId, Integer price, String categoryId) {
}
