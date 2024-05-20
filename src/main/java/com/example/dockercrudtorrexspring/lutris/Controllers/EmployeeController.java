package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Employee;
import com.example.dockercrudtorrexspring.lutris.Services.EmployeesServices;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    EmployeesServices employeesServices;

    public EmployeeController() throws NoSuchAlgorithmException {
        this.employeesServices = new EmployeesServices();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Employee>> getAll(@RequestBody Employee employee) throws SQLException {
       ArrayList<Employee> employees = this.employeesServices.getAll();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> findOne(@PathVariable("id") int id) throws SQLException {
        var result = this.employeesServices.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) throws SQLException {
        var result = this.employeesServices.create(employee);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // TODO: verificar tipo do retorno (retornar somente http status)
    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> update(@PathVariable("id") int id, @RequestBody Employee employee) throws SQLException {
        var result = this.employeesServices.findOne(id);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        result.setName(result.getDate());
        result.setDate(result.getDate());
        result.setIdSector(result.getIdSector());
        result.setIdUnit(result.getIdUnit());

        this.employeesServices.update(result);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: verificar tipo do retorno (retornar somente http status)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Employee> delete(@PathVariable("id") int id) throws SQLException {
        var result = this.employeesServices.findOne(id);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.employeesServices.delete(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
