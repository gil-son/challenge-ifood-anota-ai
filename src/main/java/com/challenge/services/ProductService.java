package com.challenge.services;


import com.challenge.domain.category.exceptions.CategoryNotFoundException;
import com.challenge.domain.product.Product;
import com.challenge.domain.product.ProductDTO;
import com.challenge.domain.product.exceptions.ProductNotFoundException;
import com.challenge.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private CategoryService categoryService;
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, CategoryService categoryService){
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    public Product insert(ProductDTO productData){
        Product newProduct = new Product(productData);
        newProduct.setCategory(categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new));
        this.productRepository.save(newProduct);
        return newProduct;
    }

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(productData.categoryId() != null) {
            this.categoryService.getById(productData.categoryId()).ifPresent(product::setCategory);
        }
        
        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setTitle(productData.title());

        this.productRepository.save(product); // MongoDB has enough intelligence to check the id and updated

        return product;
    }

    public void delete(String id){
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product); // MongoDB has enough intelligence to check the id and updated
    }
}
