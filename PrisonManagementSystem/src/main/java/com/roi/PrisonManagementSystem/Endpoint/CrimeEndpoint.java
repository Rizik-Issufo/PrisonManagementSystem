package com.roi.PrisonManagementSystem.Endpoint;

import com.roi.PrisonManagementSystem.model.Crime;
import com.roi.PrisonManagementSystem.repository.CrimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/crimes") // Ensure the base path starts with a slash
public class CrimeEndpoint {
    private final CrimeRepository crimeRepository;

    @Autowired
    public CrimeEndpoint(CrimeRepository crimeRepository) {
        this.crimeRepository = crimeRepository;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(crimeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCrimeById(@PathVariable("id") Long id) {
        Optional<Crime> crime = crimeRepository.findById(id);
        if (crime.isPresent()) {
            return new ResponseEntity<>(crime, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Crime crime) {
        return new ResponseEntity<>(crimeRepository.save(crime), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Crime> crime = crimeRepository.findById(id);
        if (crime.isPresent()) {
            crimeRepository.delete(crime.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Crime updatedCrime) {
        Optional<Crime> crime = crimeRepository.findById(id);
        if (crime.isPresent()) {
            Crime existingCrime = crime.get();
            // Update fields of the existing crime entity
            existingCrime.setCrimeType(updatedCrime.getCrimeType());
            existingCrime.setPenalty(updatedCrime.getPenalty());
            existingCrime.setDescription(updatedCrime.getDescription());
            existingCrime.setDetails(updatedCrime.getDetails());
            existingCrime.setDurationPenalty(updatedCrime.getDurationPenalty());
            existingCrime.setDetainee(updatedCrime.getDetainee());
            return new ResponseEntity<>(crimeRepository.save(existingCrime), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
