package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Employee;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;

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
        String sql = "INSERT INTO employees (nameEmployee, birth, idSector, idUnit) VALUES (?,?,?,?);";
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.setString(1, employee.getName());
        stm.setString(2, employee.getDate());
        stm.setInt(3, employee.getIdSector());
        stm.setInt(4, employee.getIdUnit());

        int rowsInserted = stm.executeUpdate();

        if(rowsInserted > 0) {
            System.out.println("Employee successfully inserted...");
            return new Employee(employee.getId(), employee.getName(), employee.getDate(),
                    employee.getIdSector(), employee.getIdUnit());
        }
        return null;
    }

    public ArrayList<Employee> getAll() throws SQLException{
        ArrayList<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM employees";
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int idE = resultSet.getInt("idEmployee");
            String name = resultSet.getString("nameEmployee");
            String birth = resultSet.getString("birth");
            int idSector = resultSet.getInt("idSector");
            int idUnit = resultSet.getInt("idUnit");

            employees.add(new Employee(idE, name, birth, idSector, idUnit));
        }

        return employees;
    }

    public Employee findOne(int id) throws SQLException{
        String sql = "SELECT * FROM employees WHERE idEmployee = " + id;
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if(!resultSet.next()) {
            return null;
        }
        int idE = resultSet.getInt("idEmployee");
        String name = resultSet.getString("nameEmployee");
        String birth = resultSet.getString("birth");
        int idSector = resultSet.getInt("idSector");
        int idUnit = resultSet.getInt("idUnit");

        return new Employee(idE, name, birth, idSector, idUnit);
    }

    public void update(Employee employee) throws SQLException {
        String sql = " UPDATE employees SET nameEmployee = "+"'"+ employee.getName() +"'" +", idUnit = ?, idSector = ? WHERE idEmployee = ? ";
        PreparedStatement stm = this.databaseRepository.getConnection().prepareStatement(sql);
        stm.setInt(1, employee.getIdSector());
        stm.setInt(2, employee.getIdUnit());
        stm.setInt(3, employee.getId());

        stm.executeUpdate();

    }

    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM employees WHERE idEmployee = " + id;
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        
        stm.executeUpdate();
    }
}
