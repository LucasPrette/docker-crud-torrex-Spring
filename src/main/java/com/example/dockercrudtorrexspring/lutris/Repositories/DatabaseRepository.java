package com.example.dockercrudtorrexspring.lutris.Repositories;

import io.github.cdimascio.dotenv.Dotenv;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;

//TODO create username, password, urlStringConnection and others APIkeys necessary as .env

public final class DatabaseRepository {

    private static DatabaseRepository instance;

    Dotenv dotenv = Dotenv.configure()
            .directory("src\\main\\resources")
            .filename(".env")
            .load();

    private DatabaseRepository() throws NoSuchAlgorithmException{

        String ConnectionURL = dotenv.get("URL");

        DatabaseRepository.instance.createConnection();

    }

    public Connection createConnection() throws NoSuchAlgorithmException {

        System.out.println("Creating SQL Server Database Connection");
        Connection connection = null;
        try{
            //provide java database driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String Connectionurl = dotenv.get("URL");
            connection = DriverManager.getConnection(Connectionurl);

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






    //TODO ver como criar conexao com banco de dado(sqlServer)
    // salvar os dados da conexao em .env
    // Extra: https://refactoring.guru/design-patterns/singleton
}
