package io.github.smallintro.redis.repository;

import io.github.smallintro.redis.model.Leave;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends CrudRepository<Leave, String> {
    List<Leave> findAll();
}
