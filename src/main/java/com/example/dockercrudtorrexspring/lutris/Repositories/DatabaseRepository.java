package com.example.dockercrudtorrexspring.lutris.Repositories;

import io.github.cdimascio.dotenv.Dotenv;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;

//TODO create username, password, urlStringConnection and others APIkeys necessary as .env

public class DatabaseRepository {

    Dotenv dotenv = Dotenv.configure()
            .directory("/docker-crud-torrex-spring/src/main/resources")
            .filename(".env")
            .load();


    String connectionURL = System.getenv("");
    public Connection createConnection() throws NoSuchAlgorithmException {

        dotenv.get(""); // returns api key from .env file

        System.out.println("Creating SQL Server Database Connection");
        Connection connection = null;
        try{
            //provide java database driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String Connectionurl = "";
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

    //TODO ver como criar conexao com banco de dado(sqlServer)
    // salvar os dados da conexao em .env
    // Extra: https://refactoring.guru/design-patterns/singleton
}
