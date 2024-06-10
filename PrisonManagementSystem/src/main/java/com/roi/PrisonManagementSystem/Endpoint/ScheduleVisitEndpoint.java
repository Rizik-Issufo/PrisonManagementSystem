package com.roi.PrisonManagementSystem.Endpoint;

import com.roi.PrisonManagementSystem.model.ScheduleVisit;
import com.roi.PrisonManagementSystem.repository.ScheduleVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/schedule_visits") // Ensure the base path starts with a slash
public class ScheduleVisitEndpoint {

    private final ScheduleVisitRepository scheduleVisitRepository;

    @Autowired
    public ScheduleVisitEndpoint(ScheduleVisitRepository scheduleVisitRepository) {
        this.scheduleVisitRepository = scheduleVisitRepository;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(scheduleVisitRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleVisitById(@PathVariable("id") Long id) {
        Optional<ScheduleVisit> scheduleVisit = scheduleVisitRepository.findById(id);
        if (scheduleVisit.isPresent()) {
            return new ResponseEntity<>(scheduleVisit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ScheduleVisit scheduleVisit) {
        return new ResponseEntity<>(scheduleVisitRepository.save(scheduleVisit), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<ScheduleVisit> scheduleVisit = scheduleVisitRepository.findById(id);
        if (scheduleVisit.isPresent()) {
            scheduleVisitRepository.delete(scheduleVisit.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ScheduleVisit updatedScheduleVisit) {
        Optional<ScheduleVisit> scheduleVisit = scheduleVisitRepository.findById(id);
        if (scheduleVisit.isPresent()) {
            ScheduleVisit existingScheduleVisit = scheduleVisit.get();
            // Update fields of the existing schedule visit entity
            existingScheduleVisit.setDetainee(updatedScheduleVisit.getDetainee());
            existingScheduleVisit.setVisitor(updatedScheduleVisit.getVisitor());
            existingScheduleVisit.setVisitDate(updatedScheduleVisit.getVisitDate());
            existingScheduleVisit.setStatus(updatedScheduleVisit.getStatus());
            existingScheduleVisit.setDescription(updatedScheduleVisit.getDescription());
            return new ResponseEntity<>(scheduleVisitRepository.save(existingScheduleVisit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
