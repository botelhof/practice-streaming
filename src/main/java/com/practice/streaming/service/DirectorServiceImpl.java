package com.practice.streaming.service;

import com.practice.streaming.dto.DirectorDto;
import com.practice.streaming.entity.Director;
import com.practice.streaming.mapper.DirectorMapper;
import com.practice.streaming.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @Override
    public DirectorDto get(String id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found"));
        return directorMapper.directorToDirectorDto(director);
    }

    @Override
    public List<DirectorDto> findAll() {
        List<Director> directors = directorRepository.findAll();
        return directors.stream()
                .map(directorMapper::directorToDirectorDto)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDto create(DirectorDto directorDto) {
        Director director = directorMapper.directorDtoToDirector(directorDto);
        Director savedDirector = directorRepository.save(director);
        return directorMapper.directorToDirectorDto(savedDirector);
    }

    @Override
    public DirectorDto update(DirectorDto directorDto) {
        Director director = directorRepository.findById(directorDto.getId())
                .orElseThrow(() -> new RuntimeException("Director not found"));

        director.setName(directorDto.getName());

        // Convert String to LocalDate if dateOfBirth is not null or empty
        if (directorDto.getDateOfBirth() != null && !directorDto.getDateOfBirth().isEmpty()) {
            director.setDateOfBirth(LocalDate.parse(directorDto.getDateOfBirth()));
        }

        Director updatedDirector = directorRepository.save(director);
        return directorMapper.directorToDirectorDto(updatedDirector);
    }


    @Override
    public void delete(String id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found"));
        directorRepository.delete(director);
    }
}
