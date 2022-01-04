package com.medical.mapper;

import com.medical.dto.PostDto;
import com.medical.model.blog.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface PostMapper {

    PostDto toDto(Post post);

    @InheritInverseConfiguration
    Post toModel(PostDto postDto);

}
