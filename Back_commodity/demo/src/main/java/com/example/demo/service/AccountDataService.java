package com.example.demo.service;

import com.example.demo.entity.AccountData;
import com.example.demo.repository.AccountDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.RegisterAccount;
import com.example.demo.repository.RegisterAccountRepository;
import org.springframework.dao.DataIntegrityViolationException;

@Service
public class AccountDataService {
    private final AccountDataRepository accountDataRepository;
    private final RegisterAccountRepository registerAccountRepository;

    @Autowired
    public AccountDataService(
            AccountDataRepository accountDataRepository,
            RegisterAccountRepository registerAccountRepository
    ) {
        this.accountDataRepository = accountDataRepository;
        this.registerAccountRepository = registerAccountRepository;
    }

    public void addData(AccountData accountData, Long id) {
        RegisterAccount registerAccount = registerAccountRepository.findById(id).orElse(null);
        if (registerAccount != null) {
            accountData.setRegisterAccount(registerAccount);
            accountDataRepository.save(accountData);
        } else {
            throw new DataIntegrityViolationException("Invalid register account id");
        }
    }
}
