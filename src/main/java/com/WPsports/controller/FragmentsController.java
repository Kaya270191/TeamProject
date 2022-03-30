package com.WPsports.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController {

    @GetMapping("/header")
    public String header(){
        return "fragments/header";
    }

    @GetMapping("/footer")
    public String footer(){
        return "fragments/footer";
    }
}
