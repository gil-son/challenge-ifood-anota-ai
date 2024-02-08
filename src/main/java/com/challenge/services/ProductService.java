package com.challenge.services;


import com.challenge.domain.category.exceptions.CategoryNotFoundException;
import com.challenge.domain.product.Product;
import com.challenge.domain.product.ProductDTO;
import com.challenge.domain.product.exceptions.ProductNotFoundException;
import com.challenge.repositories.ProductRepository;
import com.challenge.services.aws.AwsSnsService;
import com.challenge.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private CategoryService categoryService;
    private ProductRepository productRepository;
    private final AwsSnsService snsService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, AwsSnsService snsService){
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.snsService = snsService;
    }

    public Product insert(ProductDTO productData){
        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        this.productRepository.save(newProduct);
        System.out.println(newProduct.toString());
        this.snsService.publish(new MessageDTO(newProduct.toString()));
        return newProduct;
    }

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(productData.categoryId() != null) {
            this.categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);
            product.setCategory(productData.categoryId());
        }
        
        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setTitle(productData.title());

        this.productRepository.save(product); // MongoDB has enough intelligence to check the id and updated

        this.snsService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public void delete(String id){
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product); // MongoDB has enough intelligence to check the id and updated
    }
}
