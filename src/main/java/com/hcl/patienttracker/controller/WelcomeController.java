package com.hcl.patienttracker.controller;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")
public class WelcomeController {
    @GetMapping("/admin")
    @PostAuthorize("hasAnyRoles('ADMIN','DOCTOR','USER')")
    public String welcome()
    {
        System.out.println("hi  from Welcome controller");
        return "welcome";
    }
}
