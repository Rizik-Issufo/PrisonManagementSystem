package com.roi.PrisonManagementSystem.Endpoint;

import com.roi.PrisonManagementSystem.model.Movements;
import com.roi.PrisonManagementSystem.repository.MovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/movements") // Ensure the base path starts with a slash
public class MovementsEndpoint {

    private final MovementsRepository movementsRepository;

    @Autowired
    public MovementsEndpoint(MovementsRepository movementsRepository) {
        this.movementsRepository = movementsRepository;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(movementsRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovementById(@PathVariable("id") Long id) {
        Optional<Movements> movement = movementsRepository.findById(id);
        if (movement.isPresent()) {
            return new ResponseEntity<>(movement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Movements movement) {
        return new ResponseEntity<>(movementsRepository.save(movement), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Movements> movement = movementsRepository.findById(id);
        if (movement.isPresent()) {
            movementsRepository.delete(movement.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Movements updatedMovement) {
        Optional<Movements> movement = movementsRepository.findById(id);
        if (movement.isPresent()) {
            Movements existingMovement = movement.get();
            // Update fields of the existing movement entity
            existingMovement.setName(updatedMovement.getName());
            existingMovement.setWorker(updatedMovement.getWorker());
            existingMovement.setDetainee(updatedMovement.getDetainee());
            existingMovement.setDescription(updatedMovement.getDescription());
            return new ResponseEntity<>(movementsRepository.save(existingMovement), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
