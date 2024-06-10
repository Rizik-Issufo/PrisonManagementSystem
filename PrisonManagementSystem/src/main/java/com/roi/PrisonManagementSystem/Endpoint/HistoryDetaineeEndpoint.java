package com.roi.PrisonManagementSystem.Endpoint;

import com.roi.PrisonManagementSystem.model.HistoryDetainee;
import com.roi.PrisonManagementSystem.repository.HistoryDetaineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/history_detainees") // Ensure the base path starts with a slash
public class HistoryDetaineeEndpoint {

    private final HistoryDetaineeRepository historyDetaineeRepository;

    @Autowired
    public HistoryDetaineeEndpoint(HistoryDetaineeRepository historyDetaineeRepository) {
        this.historyDetaineeRepository = historyDetaineeRepository;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(historyDetaineeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHistoryDetaineeById(@PathVariable("id") Long id) {
        Optional<HistoryDetainee> historyDetainee = historyDetaineeRepository.findById(id);
        if (historyDetainee.isPresent()) {
            return new ResponseEntity<>(historyDetainee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody HistoryDetainee historyDetainee) {
        return new ResponseEntity<>(historyDetaineeRepository.save(historyDetainee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<HistoryDetainee> historyDetainee = historyDetaineeRepository.findById(id);
        if (historyDetainee.isPresent()) {
            historyDetaineeRepository.delete(historyDetainee.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody HistoryDetainee updatedHistoryDetainee) {
        Optional<HistoryDetainee> historyDetainee = historyDetaineeRepository.findById(id);
        if (historyDetainee.isPresent()) {
            HistoryDetainee existingHistoryDetainee = historyDetainee.get();
            // Update fields of the existing history detainee entity
            existingHistoryDetainee.setNumberCell(updatedHistoryDetainee.getNumberCell());
            existingHistoryDetainee.setDetainee(updatedHistoryDetainee.getDetainee());
            existingHistoryDetainee.setEntryDate(updatedHistoryDetainee.getEntryDate());
            existingHistoryDetainee.setDurationPenalty(updatedHistoryDetainee.getDurationPenalty());
            existingHistoryDetainee.setPenaltyType(updatedHistoryDetainee.getPenaltyType());
            existingHistoryDetainee.setPrisionLocal(updatedHistoryDetainee.getPrisionLocal());
            existingHistoryDetainee.setCrimeType(updatedHistoryDetainee.getCrimeType());
            existingHistoryDetainee.setDetails(updatedHistoryDetainee.getDetails());
            return new ResponseEntity<>(historyDetaineeRepository.save(existingHistoryDetainee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
