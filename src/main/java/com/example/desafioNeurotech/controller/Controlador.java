package com.example.desafioNeurotech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("")
public class Controlador {
    @RequestMapping("")
    public String getRedirectUrl() {
        return "redirect:/swagger-ui/index.html";
    }
}
