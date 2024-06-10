package com.example.demo.controller;

import com.example.demo.dtos.ProductDTO;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @GetMapping("")
    public ResponseEntity<String> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return ResponseEntity.ok(String.format("getAllCategories, page = %d, limit = %d",page, limit));
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(
            @PathVariable("id") String productId
    ){
        return ResponseEntity.ok("Product with ID: "+productId);
    }
    @PostMapping("")
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductDTO productDTO,
            BindingResult result
            ){
        try{
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Product created successfully");

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducts(@PathVariable long id){
        return ResponseEntity.ok(String.format("Product with id = %d delete successfully",id));
    }
}