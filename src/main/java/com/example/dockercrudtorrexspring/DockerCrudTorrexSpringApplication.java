package com.example.dockercrudtorrexspring;

import com.example.dockercrudtorrexspring.lutris.Entities.Dependent;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;
import com.example.dockercrudtorrexspring.lutris.Services.DependentsServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@SpringBootApplication
public class DockerCrudTorrexSpringApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
//        SpringApplication.run(DockerCrudTorrexSpringApplication.class, args);

        DatabaseRepository databaseRepository = DatabaseRepository.getInstance();

//        DependentsServices dpS = new DependentsServices();
//
//        Dependent dependent = new Dependent(0,"lucas", "08-08-2022", 1);
//        dpS.create(dependent);


    }
}
