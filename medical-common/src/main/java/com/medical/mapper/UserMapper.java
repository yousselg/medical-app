package com.medical.mapper;

import com.medical.dto.UserDto;
import com.medical.model.actors.AbstractUser;
import com.medical.model.actors.SimpleUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "displayName", source = "name")
    UserDto toDto(AbstractUser abstractUser);

    SimpleUser toModel(UserDto userDto);

}
