package com.medical.mapper;

import com.medical.dto.CategoryDto;
import com.medical.model.blog.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {
    Category toModel(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

}
