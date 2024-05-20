package com.example.dockercrudtorrexspring.lutris.Repositories;

import io.github.cdimascio.dotenv.Dotenv;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;

public final class DatabaseRepository {

    private static DatabaseRepository instance;
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    Dotenv dotenv = Dotenv.configure()
            .directory("src\\main\\resources")
            .filename(".env")
            .load();

    private DatabaseRepository() throws NoSuchAlgorithmException{
        this.createConnection();
    }

    private Connection createConnection() throws NoSuchAlgorithmException {

        System.out.println("Creating SQL Server Database Connection: ");
        Connection connection;
        try{
            //provide java database driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String ConnectionURL = dotenv.get("DATABASE_URL");
            connection = DriverManager.getConnection(ConnectionURL);

            setConnection(connection);
            
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        if(connection != null) {
            System.out.println("Connection created successfully...");
        }
        return connection;
    }

    public static DatabaseRepository getInstance() throws NoSuchAlgorithmException {
        if(instance == null) {
            instance = new DatabaseRepository();
        }

        return instance;
    }
}
