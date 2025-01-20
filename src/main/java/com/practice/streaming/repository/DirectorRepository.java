package com.practice.streaming.repository;

import com.practice.streaming.entity.Director;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends CassandraRepository<Director, String> {
}
