package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Dependent;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class DependentsServices {

    DatabaseRepository databaseRepository;
    public DependentsServices() throws NoSuchAlgorithmException {
        this.databaseRepository = DatabaseRepository.getInstance();
    }

    public Dependent create(Dependent dependent) throws SQLException {
        String sql = "INSERT INTO dependent (name, birth, idEmp) VALUES (?,?,?);";
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.setString(1,dependent.getName());
        stm.setString(2, dependent.getBirth());
        stm.setInt(3, dependent.getIdEmployee());

        int rowsInserted = stm.executeUpdate();

        if(rowsInserted > 0) {
            System.out.println("Dependent Successfully Inserted");
            return dependent;
        }
        return null;
    }

    public ArrayList<Dependent> getAll() throws SQLException {
        ArrayList<Dependent> dependents = new ArrayList<>();

        Statement statement = databaseRepository.getConnection().createStatement();
        String consult = "SELECT * FROM dependent;";
        ResultSet result = statement.executeQuery(consult);
        while(result.next()) {
            String name = result.getString("name");
            String birth = result.getString("birth");
            int idEmp = result.getInt("idEmp");
            dependents.add(new Dependent(id, name, birth, idEmp));
        }
        return dependents;
    }

    public Dependent findOne(int id) throws SQLException{
        String sql = "SELECT id, name, birth, idEmp FROM dependent WHERE id = " + id;
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        int resultId = resultSet.getInt("id");
        String resultName = resultSet.getString("name");
        String resultBirth = resultSet.getString("birth");
        int resultIdEmp = resultSet.getInt("idEmp");

        return new Dependent(resultId, resultName,resultBirth,resultIdEmp);
    }

    public void update(Dependent dependent) {
        //TODO
    }

    public void delete(int id) {
        String sql = "DELETE FROM dependents WHERE id = " + id;
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
    }
}
