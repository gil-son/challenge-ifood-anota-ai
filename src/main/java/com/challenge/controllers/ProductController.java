package com.challenge.controllers;

import com.challenge.domain.category.Category;
import com.challenge.domain.category.CategoryDTO;
import com.challenge.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class ProductController {

    private CategoryService service;

    public ProductController(CategoryService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData){
        return ResponseEntity.ok().body(this.service.insert(categoryData));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok().body(this.service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathParam("id") String id, @RequestBody CategoryDTO categoryData){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(this.service.update(id, categoryData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathParam("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
