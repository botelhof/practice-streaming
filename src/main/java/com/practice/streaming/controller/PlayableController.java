package com.practice.streaming.controller;

import com.practice.streaming.dto.PlayableDto;
import com.practice.streaming.service.PlayableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playables")
public class PlayableController {
    private final PlayableService playableService;

    public PlayableController(PlayableService playableService) {
        this.playableService = playableService;
    }

    // Get all playables
    @GetMapping
    public ResponseEntity<List<PlayableDto>> getAllPlayables() {
        return ResponseEntity.ok(playableService.findAll());
    }

    // Get a single playable by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlayableDto> getPlayableById(@PathVariable String id) {
        return ResponseEntity.ok(playableService.get(id));
    }

    // Create a new playable
    @PostMapping
    public ResponseEntity<PlayableDto> createPlayable(@RequestBody PlayableDto playableDto) {
        return ResponseEntity.ok(playableService.create(playableDto));
    }

    // Update an existing playable
    @PutMapping("/{id}")
    public ResponseEntity<PlayableDto> updatePlayable(
            @PathVariable String id,
            @RequestBody PlayableDto playableDto
    ) {
        playableDto.setId(id); // Ensure the ID matches the URL path
        return ResponseEntity.ok(playableService.update(id, playableDto));
    }

    // Delete a playable
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayable(@PathVariable String id) {
        playableService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
