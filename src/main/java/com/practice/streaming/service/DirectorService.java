package com.practice.streaming.service;

import com.practice.streaming.dto.DirectorDto;

import java.util.List;

public interface DirectorService {
    List<DirectorDto> findAll();
    DirectorDto get(String id);
    DirectorDto create(DirectorDto directorDto);
    DirectorDto update(DirectorDto directorDto);
    void delete(String id);
}
