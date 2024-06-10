package com.roi.PrisonManagementSystem.Endpoint;

import com.roi.PrisonManagementSystem.model.Detainee;
import com.roi.PrisonManagementSystem.repository.DetaineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/detainees")
public class DetaineeEndpoint {
    private final DetaineeRepository detaineeRepository;

    @Autowired
    public DetaineeEndpoint(DetaineeRepository detaineeRepository) {
        this.detaineeRepository = detaineeRepository;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(detaineeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetaineeById(@PathVariable("id") Long id) {
        Optional<Detainee> detainee = detaineeRepository.findById(id);
        if (detainee.isPresent()) {
            return new ResponseEntity<>(detainee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Detainee detainee) {
        // Additional validations can be added here
        return new ResponseEntity<>(detaineeRepository.save(detainee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Detainee> detainee = detaineeRepository.findById(id);
        if (detainee.isPresent()) {
            detaineeRepository.delete(detainee.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Detainee updatedDetainee) {
        Optional<Detainee> detainee = detaineeRepository.findById(id);
        if (detainee.isPresent()) {
            Detainee existingDetainee = detainee.get();
            // Update fields of the existing detainee entity
            existingDetainee.setName(updatedDetainee.getName());
            existingDetainee.setSurname(updatedDetainee.getSurname());
            existingDetainee.setIdCard(updatedDetainee.getIdCard());
            existingDetainee.setBirthDate(updatedDetainee.getBirthDate());
            existingDetainee.setHeight(updatedDetainee.getHeight());
            existingDetainee.setWeight(updatedDetainee.getWeight());
            existingDetainee.setPrisonDate(updatedDetainee.getPrisonDate());
            existingDetainee.setPhoto1(updatedDetainee.getPhoto1());
            existingDetainee.setPhoto2(updatedDetainee.getPhoto2());
            existingDetainee.setDescription(updatedDetainee.getDescription());
            existingDetainee.setCrimes(updatedDetainee.getCrimes());
            existingDetainee.setCellPrison(updatedDetainee.getCellPrison());
            return new ResponseEntity<>(detaineeRepository.save(existingDetainee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
