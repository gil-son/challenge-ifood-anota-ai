package com.challenge.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price; // Multiply the price by 100 to remove the decimal point
    private String category;

    public Product(ProductDTO productDTO){
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.ownerId = productDTO.ownerId();
        this.price = productDTO.price();
        this.category = productDTO.categoryId();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerId", this.ownerId);
        json.put("id", this.id);
        json.put("categoryId", this.category);
        json.put("price", this.price);
        json.put("type", "product");

        return json.toString();
    }
}
