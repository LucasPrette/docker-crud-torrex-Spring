package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Dependent;
import com.example.dockercrudtorrexspring.lutris.Services.DependentsServices;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dependents")
 public class DependentsController {

    DependentsServices dependentsServices;

    public DependentsController() throws NoSuchAlgorithmException {
        this.dependentsServices = new DependentsServices();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Dependent>> getAll(@RequestBody Dependent dependent) throws SQLException {
        ArrayList<Dependent> dependents = this.dependentsServices.getAll();

        return new ResponseEntity<>(dependents, HttpStatus.OK);
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<Dependent> findOne(@PathVariable("id") int id) throws SQLException {

        var result = this.dependentsServices.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Dependent> create(@RequestBody Dependent dependent) throws SQLException {
        var result = this.dependentsServices.create(dependent);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // TODO: verificar tipo do retorno (retornar somente http status)
    @PutMapping("/{id}")
    public ResponseEntity<Dependent> update(@PathVariable("id") int id, @RequestBody Dependent dependent) throws SQLException {
        var updateDependent = this.dependentsServices.findOne(id);

        if(updateDependent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
        updateDependent.setName(updateDependent.getName());
        updateDependent.setBirth(updateDependent.getBirth());
        updateDependent.setIdEmployee(updateDependent.getIdEmployee());

        dependentsServices.update(updateDependent);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);    
    }

    // TODO: verificar tipo do retorno (retornar somente http status)
    @DeleteMapping("/{id}")
    public ResponseEntity<Dependent> delete(@PathVariable("id") int id) throws SQLException{
        Dependent existingDependent = this.dependentsServices.findOne(id);

        if(existingDependent != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.dependentsServices.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
