package com.practice.streaming.dto;


import org.springframework.data.cassandra.core.mapping.Column;

public class PlayableDto {
    private String id;
    private String name;
    private String directorId;

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
