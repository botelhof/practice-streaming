package com.practice.streaming.service;

import com.practice.streaming.dto.PlayableDto;

import java.util.List;

public interface PlayableService {
    PlayableDto get(String id);
    List<PlayableDto> findAll();
    PlayableDto create(PlayableDto playableDto);
    PlayableDto update(String id, PlayableDto playableDto);
    void delete(String id);
}
