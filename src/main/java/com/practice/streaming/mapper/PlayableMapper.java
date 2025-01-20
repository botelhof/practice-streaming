package com.practice.streaming.mapper;

import com.practice.streaming.dto.PlayableDto;
import com.practice.streaming.entity.Playable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DirectorMapper.class)
public interface PlayableMapper {

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "directorId", source = "dto.directorId")  // Map directorId from PlayableDto to Director entity's id
    Playable playableDtoToPlayable(PlayableDto dto);

    @Mapping(target = "id", source = "playable.id")
    @Mapping(target = "name", source = "playable.name")
    @Mapping(target = "directorId", source = "playable.directorId")  // Map Director's id to PlayableDto's directorId
    PlayableDto playableToPlayableDto(Playable playable);
}
