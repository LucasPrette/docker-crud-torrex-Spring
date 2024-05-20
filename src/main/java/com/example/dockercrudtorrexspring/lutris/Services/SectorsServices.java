package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Sector;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SectorsServices {

    DatabaseRepository databaseRepository;

    public SectorsServices() throws NoSuchAlgorithmException {
        databaseRepository = DatabaseRepository.getInstance();

    }

    public Sector create(Sector sector) throws SQLException {
        String sql = "INSERT INTO sectors (name, launchDate) VALUES (?, ?);";
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.setString(1, sector.getName());
        stm.setString(2, sector.getLaunchDate());
        stm.executeUpdate();

        return sector;
    }

    public ArrayList<Sector> getAll() throws SQLException{
        //TODO save into the array
        // FIND HOW TO GET THE ID
        ArrayList<Sector> sectors = new ArrayList<>();
        String sql = "SELECT * FROM sectors";
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            String name = resultSet.getString("name");
            String launchDate = resultSet.getString("launchDate");

            sectors.add(new Sector());
        }
        return null;
    }

    public Sector findOne(int id) throws SQLException {
        // TODO: create validate if sector exists
        String sql = "SELECT * FROM sectors WHERE id =" + id;
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        return null;
    }

    public void update(Sector sector) {
        // TODO


    }

    public void delete(int id) throws SQLException{
        // TODO: validate if sector exists
        String sql = "DELETE FROM sectors WHERE id =" + id;
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
    }

}