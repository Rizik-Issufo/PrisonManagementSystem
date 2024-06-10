package com.roi.PrisonManagementSystem.repository;

import com.roi.PrisonManagementSystem.model.Movements;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementsRepository extends CrudRepository<Movements, Long> {
    List<Movements> findByName(String name);
}
