package com.example.demo.controller;

import com.example.demo.entity.RegisterAccount;
import com.example.demo.service.RegisterAccountService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

import org.slf4j.Logger;


@CrossOrigin
@RestController
@RequestMapping(path = "japi/stu13/quasar2/api/abc/register")
public class RegisterAccountController {
    private final RegisterAccountService registerAccountService;
    private final Logger LOGGER = LoggerFactory.getLogger(RegisterAccountController.class);

    @Autowired
    public RegisterAccountController(RegisterAccountService registerAccountService) {
        this.registerAccountService = registerAccountService;
    }

    @PostMapping(path = "getdata")
    public ResponseEntity<?> getAllDataId(@RequestBody RegisterAccount registerAccount) {
        try {
            String name = registerAccount.getName();
            String password = registerAccount.getPassword();
           
            ResponseEntity<?> response = registerAccountService.getAllDataId(name, password);
            if (response.getStatusCode() == HttpStatus.OK) {
                String successMessage = response.getBody().toString();
                return ResponseEntity.ok(successMessage);
            } else {
                String errorMessage = response.getBody().toString();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addNewAccount(@RequestBody RegisterAccount registerAccount) {
        LOGGER.info("Inside addNewAccount of RegisterAccountController");
        try {
            registerAccountService.addNewAccount(registerAccount);
            return ResponseEntity.ok("Success");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> upDateData(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password) {
        try {
            registerAccountService.upDateData(name, email, password);
            return ResponseEntity.ok("Success");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody RegisterAccount registerAccount) {
        try {
            String name = registerAccount.getName();
            String password = registerAccount.getPassword();
           
            ResponseEntity<?> response = registerAccountService.login(name, password);
            if (response.getStatusCode() == HttpStatus.OK) {
                String successMessage = response.getBody().toString();
                return ResponseEntity.ok(successMessage);
            } else {
                String errorMessage = response.getBody().toString();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
}
