package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Sector;
import com.example.dockercrudtorrexspring.lutris.Services.SectorsServices;
import org.springframework.http.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sectors")
public class SectorsController {

    SectorsServices sectorsServices;

    public SectorsController() {
        this.sectorsServices = new SectorsServices();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Sector>> getAll() {
        ArrayList<Sector> sectors = this.sectorsServices.getAll();

        return new ResponseEntity<>(sectors, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Sector> findOne(@PathVariable("id") int id) {
        var result = this.sectorsServices.findOne(id);

        return new ResponseEntity<>(HttpStatus.OK);

        // pegar "id" como parametro na url
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Sector> create(@RequestBody Sector sector) {
        var result = this.sectorsServices.create(sector);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
        // pegar todos os campos do body da resuisicao (JSON)
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sector> update(@PathVariable("id") int id, @RequestBody Sector sector) {
        var result = this.sectorsServices.findOne(id);

        if(result != null) {
            result.setName(result.getName());
            result.setLaunchDate(result.getLaunchDate());

            this.sectorsServices.update(result);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
        // pegar "ID" como parametro na url
        // pegar os campos do body da requisicao (JSON)
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Sector> delete(@PathVariable("id") int id) {
        var result = this.sectorsServices.findOne(id);

        if(result != null) {
            this.sectorsServices.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();

        // pegar "id" como parametro na URL
    }
}
