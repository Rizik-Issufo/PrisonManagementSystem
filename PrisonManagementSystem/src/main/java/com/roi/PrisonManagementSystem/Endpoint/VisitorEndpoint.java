package com.roi.PrisonManagementSystem.Endpoint;

import com.roi.PrisonManagementSystem.model.Visitor;
import com.roi.PrisonManagementSystem.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/visitors")
public class VisitorEndpoint {

    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorEndpoint(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(visitorRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVisitorById(@PathVariable("id") Long id) {
        Optional<Visitor> visitor = visitorRepository.findById(id);
        if (visitor.isPresent()) {
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Visitor visitor) {
        return new ResponseEntity<>(visitorRepository.save(visitor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Visitor> visitor = visitorRepository.findById(id);
        if (visitor.isPresent()) {
            visitorRepository.delete(visitor.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Visitor updatedVisitor) {
        Optional<Visitor> visitor = visitorRepository.findById(id);
        if (visitor.isPresent()) {
            Visitor existingVisitor = visitor.get();
            // Update fields of the existing visitor entity
            existingVisitor.setNameVisitor(updatedVisitor.getNameVisitor());
            existingVisitor.setIdCardVisitor(updatedVisitor.getIdCardVisitor());
            existingVisitor.setPhoneVisitor(updatedVisitor.getPhoneVisitor());
            return new ResponseEntity<>(visitorRepository.save(existingVisitor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
