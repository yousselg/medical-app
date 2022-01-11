package com.medical.mapper;

import com.medical.dto.TagDto;
import com.medical.model.blog.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TagMapper {
    Tag toModel(TagDto tagDto);

    TagDto toDto(Tag tag);
}
