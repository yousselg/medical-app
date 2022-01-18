package com.medical.mapper;

import com.medical.dto.UserDto;
import com.medical.model.actors.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserDto toDto(User user);

    User toModel(UserDto userDto);
}
