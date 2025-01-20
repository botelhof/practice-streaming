package com.practice.streaming;

import com.practice.streaming.dto.PlayableDto;
import com.practice.streaming.entity.Playable;
import com.practice.streaming.mapper.PlayableMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayableMapperTest {

    private final PlayableMapper mapper = Mappers.getMapper(PlayableMapper.class);

    @Test
    public void testMappingEntityToDto() {
        Playable entity = new Playable();
        entity.setId("123");
        entity.setName("Playable Entity");

        PlayableDto dto = mapper.playableToPlayableDto(entity);

        assertEquals("123", dto.getId());
        assertEquals("Playable Entity", dto.getName());
    }

    @Test
    public void testMappingDtoToEntity() {
        PlayableDto dto = new PlayableDto();
        dto.setId("456");
        dto.setName("Playable DTO");

        Playable entity = mapper.playableDtoToPlayable(dto);

        assertEquals("456", entity.getId());
        assertEquals("Playable DTO", entity.getName());
    }
}
