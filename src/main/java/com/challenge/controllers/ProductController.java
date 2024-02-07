package com.challenge.controllers;


import com.challenge.domain.product.Product;
import com.challenge.domain.product.ProductDTO;
import com.challenge.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productData){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(this.service.insert(productData));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok().body(this.service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productData){
        return ResponseEntity.ok().body(this.service.update(id, productData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
