package com.practice.streaming.controller;

import com.practice.streaming.dto.DirectorDto;
import com.practice.streaming.service.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    // Get all directors
    @GetMapping
    public ResponseEntity<List<DirectorDto>> getAllDirectors() {
        return ResponseEntity.ok(directorService.findAll());
    }

    // Get a specific director by ID
    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> getDirectorById(@PathVariable String id) {
        return ResponseEntity.ok(directorService.get(id));
    }

    // Create a new director
    @PostMapping
    public ResponseEntity<DirectorDto> createDirector(@RequestBody DirectorDto directorDto) {
        DirectorDto createdDirector = directorService.create(directorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDirector);
    }

    // Update an existing director
    @PutMapping("/{id}")
    public ResponseEntity<DirectorDto> updateDirector(@PathVariable String id, @RequestBody DirectorDto directorDto) {
        directorDto.setId(id); // Ensure the ID matches the path variable
        DirectorDto updatedDirector = directorService.update(directorDto);
        return ResponseEntity.ok(updatedDirector);
    }

    // Delete a director by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable String id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
