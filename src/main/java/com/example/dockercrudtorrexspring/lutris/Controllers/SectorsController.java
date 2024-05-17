package com.example.dockercrudtorrexspring.lutris.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sectors")
public class SectorsController {

    @ResponseBody
    void getAll() {

    }

    @ResponseBody
    void findOne() {
        // pegar "id" como parametro na url
    }

    @ResponseBody
    void create() {
        // pegar todos os campos do body da resuisicao (JSON)
    }

    @ResponseBody
    void update() {
        // pegar "ID" como parametro na url
        // pegar os campos do body da requisicao (JSON)
    }

    void delete() {
        // pegar "id" como parametro na URL
    }
}
