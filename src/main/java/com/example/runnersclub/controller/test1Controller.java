package com.example.runnersclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class test1Controller {
    @GetMapping(value = "/ex")
    public String selectImageList() {

        return "/main";
    }
}