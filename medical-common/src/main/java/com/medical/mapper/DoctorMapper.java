package com.medical.mapper;

import com.medical.dto.DoctorDto;
import com.medical.model.actors.Doctor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface DoctorMapper {

    DoctorDto toDto(Doctor doctor);

    @InheritInverseConfiguration
    Doctor toModel(DoctorDto postDto);

}
