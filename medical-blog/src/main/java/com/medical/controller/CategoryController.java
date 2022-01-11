package com.medical.controller;

import com.medical.dto.ApiResponse;
import com.medical.dto.CategoryDto;
import com.medical.mapper.CategoryMapper;
import com.medical.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;
    @Autowired
    private CategoryMapper mapper;

    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        final List<CategoryDto> categoryDtos = this.service.findAll().stream().map(this.mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, categoryDtos));
    }

}
