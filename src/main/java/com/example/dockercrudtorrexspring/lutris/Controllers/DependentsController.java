package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Dependent;
import com.example.dockercrudtorrexspring.lutris.Services.DependentsServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dependents")
 public class DependentsController {

    DependentsServices dependentsServices;

    public DependentsController() {
        this.dependentsServices = new DependentsServices();
    }

    @ResponseBody
    void getAll() {

    }

    @GetMapping(path="/{id}", produces = "application/json")
    public String findOne(@PathVariable("id") int id) {

        var result = this.dependentsServices.findOne(id);

        System.out.println(id);
        // pegar "id" como parametro na url

        return String.valueOf(new ResponseEntity<>(HttpStatus.OK));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Dependent> create(@RequestBody Dependent dependent) {
        var result = this.dependentsServices.create(dependent);

        return new ResponseEntity<>(result, HttpStatus.CREATED);

        // pegar todos os campos do body da resquisicao (JSON)
    }

    @PutMapping("/{id}")
    public ResponseEntity<> update(@PathVariable("id") int id, @RequestBody Dependent dependent) {
        var updateDependent = this.dependentsServices.findOne(id);

        if(updateDependent != null) {
            updateDependent.setName(updateDependent.getName());
            updateDependent.setBirth(updateDependent.getBirth());
            updateDependent.setIdEmployee(updateDependent.getIdEmployee());

            dependentsServices.update(updateDependent);
            
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();

        // pegar "ID" como parametro na url
        // pegar os campos do body da requisicao (JSON)
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<> delete(@PathVariable("id") int id) {
        Dependent existingDependent = this.dependentsServices.findOne(id);

        if(existingDependent != null) {
            this.dependentsServices.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();

        //TODO find how to return no content response entity


        // pegar "id" como parametro na URL
    }
}
