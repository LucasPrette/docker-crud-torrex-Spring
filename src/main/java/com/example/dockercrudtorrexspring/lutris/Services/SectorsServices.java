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
        String sql = "insert into sectors (nameSector, launchDate) VALUES (?, GETDATE());";
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.setString(1, sector.getName());

        int rowsInserted = stm.executeUpdate();

        if(rowsInserted > 0) {
            return new Sector(sector.getId(), sector.getName(), sector.getLaunchDate());
        }

        return sector;
    }

    public ArrayList<Sector> getAll() throws SQLException{
        ArrayList<Sector> sectors = new ArrayList<>();

        String sql = "SELECT * FROM sectors";
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int id = resultSet.getInt("idSector");
            String name = resultSet.getString("nameSector");
            String launchDate = resultSet.getString("launchDate");

            sectors.add(new Sector(id, name, launchDate));
        }

        return sectors;
    }

    public Sector findOne(int id) throws SQLException {
        String sql = "SELECT * FROM sectors WHERE idSector =" + id;
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if(!resultSet.next()){
            return null;
        }
        int idU = resultSet.getInt("idSector");
        String name = resultSet.getString("nameSector");
        String launchDate = resultSet.getString("launchDate");
        return new Sector(idU, name, launchDate);

    }

    public void update(Sector sector) throws SQLException {
        String sql = "UPDATE sectors SET nameSector = " + " '" + sector.getName() + "' " +" WHERE idSector = " + sector.getId();
        PreparedStatement stm = this.databaseRepository.getConnection().prepareStatement(sql);
        stm.executeUpdate();
    }

    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM sectors WHERE idSector = " + id;
        PreparedStatement stm = databaseRepository.getConnection().prepareStatement(sql);
        stm.executeUpdate();
    }

}