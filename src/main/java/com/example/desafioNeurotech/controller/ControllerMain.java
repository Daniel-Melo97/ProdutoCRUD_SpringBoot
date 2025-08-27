package com.example.desafioNeurotech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("")
public class ControllerMain {
    @RequestMapping("")
    public String getRedirectUrl() {//Sempre que tentar acessar o 'localhost:8081/', redireciona para a página do swagger 
        return "redirect:/swagger-ui/index.html";
    }
}
