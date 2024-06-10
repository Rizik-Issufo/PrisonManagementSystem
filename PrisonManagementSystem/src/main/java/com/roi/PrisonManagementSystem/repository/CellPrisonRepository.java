package com.roi.PrisonManagementSystem.repository;

import com.roi.PrisonManagementSystem.model.CellPrison;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CellPrisonRepository extends CrudRepository<CellPrison, Long> {
    List<CellPrison> findByNumberCell(int numberCell);
}
