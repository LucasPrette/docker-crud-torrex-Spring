package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Employee;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;

import javax.swing.plaf.nimbus.State;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeesServices {
    DatabaseRepository databaseRepository;

    public EmployeesServices() throws NoSuchAlgorithmException {
         databaseRepository = DatabaseRepository.getInstance();
    }


    public Employee create(Employee employee) throws SQLException {
        //TODO: save all atributes
        String sql = "INSERT INTO employee () VALUES (?,?,?,?);";
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.setString(1, employee.getName());
        stm.setString();
        stm.setString();
        stm.setString();

        int rowsInserted = stm.executeUpdate();

        if(rowsInserted > 0) {
            System.out.println("Employee successfully inserted...");
            return employee;
        }
    }

    public ArrayList<Employee> getAll() throws SQLException{
        //TODO save all atributes
        ArrayList<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM employees";
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {

            employees.add(new Employee());
            return employees;
        }

        return null;
    }

    public Employee findOne(int id) throws SQLException{
        String sql = "SELECT * FROM employees WHERE id = " + id;
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        //TODO save and return all data


        return null;
    }

    public Employee update(Employee employee) {
        // TODO
        return null;
    }

    public Employee delete(int id) throws SQLException{
        // TODO validate if employee exists
        String sql = "DELETE FROM employees WHERE id = " + id;
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.executeUpdate();

        return null;
    }
}
