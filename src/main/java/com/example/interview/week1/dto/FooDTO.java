package com.example.interview.week1.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FooDTO {
    private Long id;
    @NotBlank(message = "Name must not be blank")
    @Length(min = 2, max = 16, message = "Name must be between 2 and 16 characters")
    private String name;
    @Length(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}
