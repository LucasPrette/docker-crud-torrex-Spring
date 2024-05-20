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
        String sql = "INSERT INTO units (name, lauchDate) VALUES (?, ?);";
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.setString(1, unit.getName());
        stm.setString(2, unit.getLaunchDate());
        stm.executeUpdate();

        return null;
    }

    public ArrayList<Unit> getAll() throws SQLException {
        ArrayList<Unit> units = new ArrayList<>();
        String sql = "SELECT * FROM units";
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String launchDate = resultSet.getString("launchDate");

            units.add(new Unit(id, name, launchDate));
        }
        return null;
    }

    public Unit findOne(int id) throws SQLException {
        String sql = "SELECT * FROM units WHERE id = " + id;
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        return null;
    }

    public Unit update(Unit unit) {
        //TODO
        return null;
    }

    public Unit delete(int id) throws SQLException {
        //TODO validate unit

        String sql = "DELETE FROM units WHERE id = " + id;
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.executeUpdate();

        return null;
    }
}
