package com.example.dockercrudtorrexspring;

import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class DockerCrudTorrexSpringApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        SpringApplication.run(DockerCrudTorrexSpringApplication.class, args);

        DatabaseRepository databaseRepository = DatabaseRepository.getInstance();

        databaseRepository.createConnection();


    }
}
