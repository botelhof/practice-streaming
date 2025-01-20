package com.practice.streaming.repository;

import com.practice.streaming.entity.Playable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayableRepository extends CassandraRepository<Playable, String> {
}
