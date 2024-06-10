package com.roi.PrisonManagementSystem.repository;

import com.roi.PrisonManagementSystem.model.HistoryDetainee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryDetaineeRepository extends CrudRepository<HistoryDetainee, Long> {
    List<HistoryDetainee> findByNumberCell(int numberCell);
}
