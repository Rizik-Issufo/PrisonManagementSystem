package com.roi.PrisonManagementSystem.repository;

import com.roi.PrisonManagementSystem.model.Crime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimeRepository extends CrudRepository<Crime, Long> {
    List<Crime> findByCrimeType(String crimeType);
}
