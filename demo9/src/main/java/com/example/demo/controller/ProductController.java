package com.example.demo.controller;

import com.example.demo.dtos.ProductDTO;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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
            List<MultipartFile> files = productDTO.getFiles();
            files = files== null ? new ArrayList<MultipartFile>() : files;
            for(MultipartFile file : files){
                if(file.getSize() == 0){
                    continue;
                }
                if(file.getSize() > 10 *1024*1024){
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                        .body("File is too large! Maximum size is 10MB");
            }
                String contentType = file.getContentType();
                if(contentType == null || contentType.startsWith("images/")){
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File must be image");
                }
                String filenames = storeFile(file);
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
    public String storeFile(MultipartFile file) throws IOException{
//        String filename = StringUtils.cleanPath(file.getOriginalFilename());
//        String uniqueFilename = UUID.randomUUID().toString() + "_"+ filename;
//        Path uploadDir = Paths.get("uploads");
//        if(!Files.exists(uploadDir)){
//            Files.createDirectories(uploadDir);
//        }
//        Path destination = Paths.get(uploadDir.toString(),uniqueFilename.toLowerCase(Locale.ROOT));
//        Files.copy(file.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
//        return uniqueFilename;


//        String filename = StringUtils.cleanPath(file.getOriginalFilename());
//        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
//        Path uploadDir = Paths.get("uploads");
//        if (!Files.exists(uploadDir)) {
//            Files.createDirectories(uploadDir);
//        }
//        Path destination = uploadDir.resolve(uniqueFilename);
//        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
//        return uniqueFilename;
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
        Path uploadDir = Paths.get("uploads");

        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        try (var inputStream = file.getInputStream()) {
            Path destination = uploadDir.resolve(uniqueFilename).normalize();
            Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFilename;
        } catch (IOException e) {
            throw new IOException("Could not store file " + filename + ". Please try again!", e);
        }
    }
}

