package com.example.demo.controller;

import com.example.demo.dtos.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @GetMapping("")
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return ResponseEntity.ok(String.format("getAllCategories, page = %d, limit = %d",page, limit));
    }

    @PostMapping("")
    public ResponseEntity<?> insertCategory(
            @Valid  @RequestBody CategoryDTO categoryDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            List<String> errorsMessages = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorsMessages);
        }

        // If validation succeeds, continue with your logic
        return ResponseEntity.ok("CategoryDTO is valid: " + categoryDTO);
    }

}
