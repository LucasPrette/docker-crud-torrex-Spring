package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Unit;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UnitsServices {

    DatabaseRepository databaseRepository;

    public UnitsServices() throws NoSuchAlgorithmException {
        databaseRepository = DatabaseRepository.getInstance();
    }

    public Unit create(Unit unit) throws SQLException {
        String sql = "insert into units (city, launchDate) values (?, ?);";
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.setString(1, unit.getName());
        stm.setString(2, unit.getLaunchDate());

        int rowsInsert = stm.executeUpdate();

        if(rowsInsert > 0) {
            System.out.println("Successfully inserted");
        }

        // TODO: return unit with complete data populated (id, createdAt, ...)
        return unit;
    }

    public ArrayList<Unit> getAll() throws SQLException {
        ArrayList<Unit> units = new ArrayList<>();

        String sql = "SELECT * FROM units";
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int id = resultSet.getInt("idUnit");
            String name = resultSet.getString("city");
            String launchDate = resultSet.getString("launchDate");

            units.add(new Unit(id, name, launchDate));
        }

        return units;
    }

    public Unit findOne(int id) throws SQLException {
        String sql = "SELECT * FROM units WHERE idUnit = " + id;
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            int idR = resultSet.getInt("idUnit");
            String name = resultSet.getString("city");
            String launchDate = resultSet.getString("launchDate");
            return new Unit(idR, name, launchDate);
        }

        return null;
    }

    public Unit update(Unit unit) {
        //TODO: implement it
        return null;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM units WHERE idUnit = " + id;
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.executeUpdate();
    }
}
