package com.example.demo.service;

import com.example.demo.entity.RegisterAccount;
import com.example.demo.repository.RegisterAccountRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.demo.entity.AccountData;

@Service
public class RegisterAccountService {
private final RegisterAccountRepository registerAccountRepository;
    @Autowired
    public RegisterAccountService(RegisterAccountRepository registerAccountRepository) {
        this.registerAccountRepository = registerAccountRepository;
    }

    public ResponseEntity<?> getAllDataId(String name, String password) {
        Optional<RegisterAccount> accountOptional = registerAccountRepository.findAccountByName(name);
        if (accountOptional.isPresent()) {
            RegisterAccount account = accountOptional.get();
            if (account.getPassword().equals(password)) {
                
                Long accountId = account.getId();
                return ResponseEntity.ok(accountId);
            }
        }
        // 處理登入失敗的邏輯
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("帳號密碼錯誤");
    }


    public void addNewAccount(RegisterAccount registerAccount){
        Optional<RegisterAccount> accountByEmail = registerAccountRepository.findAccountByEmail(registerAccount.getEmail());
        if(accountByEmail.isPresent()){
            throw new DataIntegrityViolationException("email taken");
        }
        Optional<RegisterAccount> accountByName = registerAccountRepository.findAccountByName(registerAccount.getName());
        if(accountByName.isPresent()){
            throw new DataIntegrityViolationException("name taken");
        }
        registerAccountRepository.save(registerAccount);
        

    }
    public void upDateData(String name, String email, String password){


        RegisterAccount registerAccount = registerAccountRepository
                .findAccountByName(name).orElseThrow(() ->new DataIntegrityViolationException("Account dose not exist"));
        if(name.equals(registerAccount.getName()) && password.equals(registerAccount.getPassword()) ){
            Optional<RegisterAccount> accountByEmail = registerAccountRepository.findAccountByEmail(email);
            if(accountByEmail.isPresent()){
                throw new DataIntegrityViolationException("email taken");
            }
            registerAccount.setEmail(email);
        }


        if(name.equals(registerAccount.getName()) && email.equals(registerAccount.getEmail())){
            registerAccount.setPassword(password);
        }
        if(name.equals(registerAccount.getName()) && !password.equals(registerAccount.getPassword()) && !email.equals(registerAccount.getEmail())){
          throw new DataIntegrityViolationException ("data is wrong");
        }
      
       registerAccountRepository.save(registerAccount);
       
    }
    public ResponseEntity<?> login(String name, String password) {
        Optional<RegisterAccount> accountOptional = registerAccountRepository.findAccountByName(name);
        if (accountOptional.isPresent()) {
            RegisterAccount account = accountOptional.get();
            if (account.getPassword().equals(password)) {
                
                Long accountId = account.getId();
                List<AccountData> accountDataList = registerAccountRepository.findAccountDataById(accountId);
                return ResponseEntity.ok(accountDataList);
            }
        }
        // 處理登入失敗的邏輯
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("帳號密碼錯誤");
    }

    
}
