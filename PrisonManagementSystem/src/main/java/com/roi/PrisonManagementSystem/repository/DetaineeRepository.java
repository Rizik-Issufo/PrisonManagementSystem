package com.roi.PrisonManagementSystem.repository;

import com.roi.PrisonManagementSystem.model.Detainee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetaineeRepository extends CrudRepository<Detainee, Long> {
    List<Detainee> findByName(String name);
}
