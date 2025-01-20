package com.practice.streaming.mapper;

import com.practice.streaming.dto.DirectorDto;
import com.practice.streaming.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    // Director to DirectorDto mapping
    @Mapping(target = "id", source = "director.id")
    @Mapping(target = "name", source = "director.name")
    @Mapping(target = "dateOfBirth", source = "director.dateOfBirth")
    DirectorDto directorToDirectorDto(Director director);

    // DirectorDto to Director mapping
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "dateOfBirth", source = "dto.dateOfBirth")
    Director directorDtoToDirector(DirectorDto dto);
}
