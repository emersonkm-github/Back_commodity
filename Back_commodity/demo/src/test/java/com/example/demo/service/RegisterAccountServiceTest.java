package com.example.demo.service;

import com.example.demo.entity.RegisterAccount;
import com.example.demo.repository.RegisterAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class RegisterAccountServiceTest {
    @MockBean
    private RegisterAccountRepository registerAccountRepository;

    @Autowired
    private RegisterAccountService registerAccountService;

    @BeforeEach
    void setUp() {
        RegisterAccount account = RegisterAccount.builder()
                .name("mingming2")
                .password("mingming2")
                .id(3L)
                .build();


        // Mock the repository method to return the account
        Mockito.when(registerAccountRepository.findAccountByName("mingming2"))
                .thenReturn(Optional.of(account));
    }

    @Test
    @DisplayName("Get Id by valid account && password")
    @Disabled
    public void whenValidGetDataId_thenShouldFoundDataId() {
        ResponseEntity<?> allDataId = registerAccountService.getAllDataId("mingming2", "mingming2");
        assertEquals(HttpStatus.OK, allDataId.getStatusCode());
        assertEquals(3L, allDataId.getBody());
    }

    @Test
    @DisplayName("addNewAccount with all empty")
    @Disabled
    public void whenAddNewAccount_thenAccountAdded() {
        // 创建一个样例的RegisterAccount对象
        RegisterAccount account = RegisterAccount.builder()
                .name("mingming5")
                .email("mingming5@gmail.com")
                .password("mingming5")
                .build();

        // 模拟存储库返回一个空的Optional
        when(registerAccountRepository.findAccountByEmail(account.getEmail()))
                .thenReturn(Optional.empty());
        when(registerAccountRepository.findAccountByName(account.getName()))
                .thenReturn(Optional.empty());

        // 调用方法添加账户
        registerAccountService.addNewAccount(account);

        // 验证存储库的save方法是否被调用，并且参数是account对象
        verify(registerAccountRepository, times(1)).save(account);
    }

    @Test
    @DisplayName("addNewAccount_emailTaken")
    @Disabled
    public void addNewAccount_emailTaken(){
        RegisterAccount account = RegisterAccount.builder()
                .name("mingming5")
                .email("mingming5@gmail.com")
                .password("mingming5")
                .build();
        when(registerAccountRepository.findAccountByEmail(account.getEmail()))
                .thenReturn(Optional.of(account));
        assertThrows(DataIntegrityViolationException.class, () -> {
            registerAccountService.addNewAccount(account);
        });
        verify(registerAccountRepository, never()).save(account);


    }

    @Test
    @DisplayName("addNewAccount_nameTaken")
    @Disabled
    public void addNewAccount_nameTaken(){
        RegisterAccount account = RegisterAccount.builder()
                .name("mingming5")
                .email("mingming5@gmail.com")
                .password("mingming5")
                .build();
        when(registerAccountRepository.findAccountByName(account.getName()))
                .thenReturn(Optional.of(account));
        assertThrows(DataIntegrityViolationException.class, () -> {
            registerAccountService.addNewAccount(account);
        });
        verify(registerAccountRepository, never()).save(account);


    }
}
