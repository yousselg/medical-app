package com.medical.mapper;

import com.medical.dto.CommentDto;
import com.medical.model.blog.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<CommentDto> toDto(List<Comment> comment);

    @InheritInverseConfiguration
    Comment toModel(CommentDto commentDto);

}
