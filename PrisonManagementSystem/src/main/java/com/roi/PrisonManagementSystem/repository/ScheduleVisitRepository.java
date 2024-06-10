package com.roi.PrisonManagementSystem.repository;

import com.roi.PrisonManagementSystem.model.ScheduleVisit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleVisitRepository extends CrudRepository<ScheduleVisit, Long> {
    List<ScheduleVisit> findByDetaineeId(long detaineeId);
}
