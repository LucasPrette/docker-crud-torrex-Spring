package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Employee;
import com.example.dockercrudtorrexspring.lutris.Services.EmployeesServices;
import org.springframework.http.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
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

        // pegar "id" como parametro na url
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) throws SQLException {
        var result = this.employeesServices.create(employee);

        return new ResponseEntity<>(result, HttpStatus.OK);
        // pegar todos os campos do body da resuisicao (JSON)
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> update(@PathVariable("id") int id, @RequestBody Employee employee) throws SQLException {
        var result = this.employeesServices.findOne(id);

        if(result != null) {
            result.setName(result.getDate());
            result.setDate(result.getDate());
            result.setIdSector(result.getIdSector());
            result.setIdUnit(result.getIdUnit());

            this.employeesServices.update(result);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();

        // pegar "ID" como parametro na url
        // pegar os campos do body da requisicao (JSON)
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Employee> delete(@PathVariable("id") int id) throws SQLException {
        var result = this.employeesServices.findOne(id);

        if(result != null) {
            this.employeesServices.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.notFound().build();

        // pegar "id" como parametro na URL
    }
}
