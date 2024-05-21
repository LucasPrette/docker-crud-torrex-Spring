package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Unit;
import com.example.dockercrudtorrexspring.lutris.Services.UnitsServices;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path ="/units")
public class UnitsController {

    UnitsServices unitsServices;

    public UnitsController() throws NoSuchAlgorithmException {
        this.unitsServices = new UnitsServices();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Unit>> getAll(@RequestBody Unit unit) throws SQLException {
        ArrayList<Unit> units = this.unitsServices.getAll();

        return new ResponseEntity<>(units, HttpStatus.OK);
    }


    @GetMapping(path ="/{id}", produces = "application/json")
    public ResponseEntity<Unit> findOne(@PathVariable("id") int id) throws SQLException {

        var result = this.unitsServices.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Unit> create(@RequestBody Unit unit) throws SQLException {
        var result = this.unitsServices.create(unit);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Unit unit) throws SQLException {
        var result = this.unitsServices.findOne(id);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        result.setName(unit.getName());

        this.unitsServices.update(result);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws SQLException {
        var result = this.unitsServices.findOne(id);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.unitsServices.delete(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
