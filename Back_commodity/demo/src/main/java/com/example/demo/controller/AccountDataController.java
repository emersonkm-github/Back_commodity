package com.example.demo.controller;

import com.example.demo.service.AccountDataService;
import com.example.demo.entity.AccountData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

@CrossOrigin
@RestController
@RequestMapping(path = "japi/stu13/quasar2/api/abc/data")
public class AccountDataController {
    private final AccountDataService accountDataService;
    @Autowired
    public AccountDataController(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }
    @PostMapping
    public ResponseEntity<?> addData (@RequestBody AccountData accountData, @RequestParam Long id){
        try {
            accountDataService.addData(accountData,id);
            return ResponseEntity.ok("Success");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
 
    }

