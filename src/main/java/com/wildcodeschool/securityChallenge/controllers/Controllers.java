package com.wildcodeschool.securityChallenge.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controllers {
	
	
    @GetMapping("/")
    public String hello() {
        return "Welcome to the SHIELD";
    }
    
    @GetMapping("/avengers/assemble")
    public String shieldShampions() {
        return "Avengers... Assemble";
    }
    
    @GetMapping("/secret-bases")
    public String secret() {
        return "liste des villes de la wild (comment ca j'ai la flemme? ;))";
    }
}
