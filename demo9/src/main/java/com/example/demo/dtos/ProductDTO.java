package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
//    @NotBlank(message = "Title is required")
//    @Size(min = 3, max = 200, message = "title must be between 3 and 200 characters")
//    private String name;
//
//    @Min(value = 0, message = "Price must be greater than or equal to 0")
//    @Max(value = 1000000000, message = "Price must be less than or equal to 10000000000")
//    private Float price;
//    private String thumbnail;
//
//    @JsonProperty("category_id")
//    private String categoryId;
//
//    private List<MultipartFile> files;
@NotBlank(message = "Title is required")
@Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
private String name;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 1000000000, message = "Price must be less than or equal to 10000000000")
    private Float price;

    private String thumbnail;

    @JsonProperty("category_id")
    private String categoryId;

    @NotNull(message = "Files cannot be null")
    private List<MultipartFile> files;

}
