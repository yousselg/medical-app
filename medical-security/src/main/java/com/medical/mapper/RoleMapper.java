package com.medical.mapper;

import com.medical.model.actors.Role;
import com.medical.repository.RoleRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = RoleRepository.class)
public abstract class RoleMapper {

    @Autowired
    private RoleRepository roleRepository;

    public String toString(final Role role) {
        return role.getName();
    }

    @Named("toModel")
    public Role toModel(final String role) {
        return this.roleRepository.findByName(role);
    }

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL, qualifiedByName = "toModel")
    public abstract Set<Role> toModel(final Set<String> role);


}
