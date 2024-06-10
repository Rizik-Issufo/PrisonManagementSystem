package com.roi.PrisonManagementSystem.Endpoint;

import com.roi.PrisonManagementSystem.model.CellPrison;
import com.roi.PrisonManagementSystem.repository.CellPrisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cell_prisons") // Changed to plural for consistency
public class CellPrisonEndpoint {

    private final CellPrisonRepository cellPrisonRepository;

    @Autowired
    public CellPrisonEndpoint(CellPrisonRepository cellPrisonRepository) {
        this.cellPrisonRepository = cellPrisonRepository;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(cellPrisonRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCellPrisonById(@PathVariable("id") Long id) {
        Optional<CellPrison> cellPrison = cellPrisonRepository.findById(id);
        if (cellPrison.isPresent()) {
            return new ResponseEntity<>(cellPrison, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CellPrison cellPrison) {
        return new ResponseEntity<>(cellPrisonRepository.save(cellPrison), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<CellPrison> cellPrison = cellPrisonRepository.findById(id);
        if (cellPrison.isPresent()) {
            cellPrisonRepository.delete(cellPrison.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CellPrison updatedCellPrison) {
        Optional<CellPrison> cellPrison = cellPrisonRepository.findById(id);
        if (cellPrison.isPresent()) {
            CellPrison existingCellPrison = cellPrison.get();
            // Update fields of the existing cell entity
            existingCellPrison.setNumberCell(updatedCellPrison.getNumberCell());
            existingCellPrison.setFloor(updatedCellPrison.getFloor());
            existingCellPrison.setCapacity(updatedCellPrison.getCapacity());
            existingCellPrison.setDetainees(updatedCellPrison.getDetainees());
            existingCellPrison.setCategoryCell(updatedCellPrison.getCategoryCell());
            return new ResponseEntity<>(cellPrisonRepository.save(existingCellPrison), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
