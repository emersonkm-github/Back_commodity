package com.example.demo.repository;

import com.example.demo.entity.RegisterAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegisterAccountRepositoryTest {
    @Autowired
    private RegisterAccountRepository registerAccountRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        RegisterAccount registerAccount = RegisterAccount.builder()
                .name("test1")
                .email("test1@gmail.com")
                .password("test1")
                .build();

        testEntityManager.persist(registerAccount);
    }
    @Test
    @Disabled
    public void whenFindByEmail_thenReturnRegisterAccount(){
        RegisterAccount registerAccount = registerAccountRepository.findAccountByEmail("test1@gmail.com").get();

        assertEquals(registerAccount.getName(),"test1");
    }
    @Test
    public  void whenFindByName_thenReturnRegisterAccount(){
        RegisterAccount registerAccount = registerAccountRepository.findAccountByName("test1").get();

        assertEquals(registerAccount.getName(),"test1");
    }
}