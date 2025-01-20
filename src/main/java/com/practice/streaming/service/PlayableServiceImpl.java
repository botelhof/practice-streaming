package com.practice.streaming.service;

import com.practice.streaming.dto.PlayableDto;
import com.practice.streaming.entity.Playable;
import com.practice.streaming.mapper.PlayableMapper;
import com.practice.streaming.repository.PlayableRepository;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayableServiceImpl implements PlayableService {
    private final PlayableRepository playableRepository;
    private final PlayableMapper playableMapper;
    private final Counter playableCreatedCounter;
    private final Counter playableViewedCounter;

    public PlayableServiceImpl(PlayableRepository playableRepository, PlayableMapper playableMapper,
                               Counter playableCreatedCounter, Counter playableViewedCounter) {
        this.playableRepository = playableRepository;
        this.playableMapper = playableMapper;
        this.playableCreatedCounter = playableCreatedCounter;
        this.playableViewedCounter = playableViewedCounter;
    }

    @Override
    public PlayableDto get(String id) {
        Playable playable = playableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playable not found"));
        playableViewedCounter.increment();
        return playableMapper.playableToPlayableDto(playable);
    }

    @Override
    public List<PlayableDto> findAll() {
        List<Playable> playables = playableRepository.findAll();
        return playables.stream()
                .map(playableMapper::playableToPlayableDto)
                .collect(Collectors.toList());
    }

    // Create a new Playable
    @Override
    public PlayableDto create(PlayableDto playableDto) {
        // Convert PlayableDto to Playable entity
        Playable playable = playableMapper.playableDtoToPlayable(playableDto);
        // Save the Playable entity to the database
        Playable savedPlayable = playableRepository.save(playable);
        // Increment the playout created counter
        playableCreatedCounter.increment();
        // Return the saved Playable as PlayableDto
        return playableMapper.playableToPlayableDto(savedPlayable);
    }

    // Update an existing Playable by ID
    @Override
    public PlayableDto update(String id, PlayableDto playableDto) {
        // Check if Playable exists
        Playable existingPlayable = playableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playable not found"));
        // Map PlayableDto to Playable
        Playable updatedPlayable = playableMapper.playableDtoToPlayable(playableDto);
        // Set the ID to the existing Playable ID
        updatedPlayable.setId(existingPlayable.getId());
        // Save the updated Playable entity
        Playable savedPlayable = playableRepository.save(updatedPlayable);
        // Return the updated Playable as PlayableDto
        return playableMapper.playableToPlayableDto(savedPlayable);
    }

    // Delete a Playable by ID
    @Override
    public void delete(String id) {
        // Check if Playable exists
        Playable existingPlayable = playableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playable not found"));
        // Delete the Playable entity
        playableRepository.delete(existingPlayable);
    }
}
