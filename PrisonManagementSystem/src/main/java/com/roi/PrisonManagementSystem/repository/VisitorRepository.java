package com.roi.PrisonManagementSystem.repository;

import com.roi.PrisonManagementSystem.model.Visitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Long> {
    List<Visitor> findByNameVisitor(String nameVisitor);
}
