package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Sector;
import com.example.dockercrudtorrexspring.lutris.Services.SectorsServices;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sectors")
public class SectorsController {

    SectorsServices sectorsServices;

    public SectorsController() throws NoSuchAlgorithmException {
        this.sectorsServices = new SectorsServices();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Sector>> getAll() throws SQLException {
        ArrayList<Sector> sectors = this.sectorsServices.getAll();

        return new ResponseEntity<>(sectors, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Sector> findOne(@PathVariable("id") int id) throws SQLException {
        var result = this.sectorsServices.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Sector> create(@RequestBody Sector sector) throws SQLException {
        var result = this.sectorsServices.create(sector);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // TODO: verificar tipo do retorno (retornar somente http status)
    @PutMapping("/{id}")
    public ResponseEntity<Sector> update(@PathVariable("id") int id, @RequestBody Sector sector) throws SQLException {
        var result = this.sectorsServices.findOne(id);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        result.setName(result.getName());
        result.setLaunchDate(result.getLaunchDate());

        this.sectorsServices.update(result);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: verificar tipo do retorno (retornar somente http status)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Sector> delete(@PathVariable("id") int id) throws SQLException {
        var result = this.sectorsServices.findOne(id);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.sectorsServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
