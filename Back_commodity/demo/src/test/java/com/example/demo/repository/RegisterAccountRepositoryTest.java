package com.example.demo.repository;

import com.example.demo.entity.AccountData;
import com.example.demo.entity.RegisterAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegisterAccountRepositoryTest {
    @Autowired
    private RegisterAccountRepository registerAccountRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    private RegisterAccount registerAccount;
    private AccountData accountData1;
    private AccountData accountData2;
    private AccountData accountData3;

    @BeforeEach
    void setUp() {
        registerAccount = RegisterAccount.builder()
                .name("test1")
                .email("test1@gmail.com")
                .password("test1")
                .build();
        accountData1 = AccountData.builder()
                .goods("BTC")
                .amount(200D)
                .registerAccount(registerAccount)
                .build();
        accountData2 = AccountData.builder()
                .goods("ETH")
                .amount(200D)
                .registerAccount(registerAccount)
                .build();
        accountData3 = AccountData.builder()
                .goods("BNB")
                .amount(200D)
                .registerAccount(registerAccount)
                .build();

        testEntityManager.persist(registerAccount);
        testEntityManager.persist(accountData1);
        testEntityManager.persist(accountData2);
        testEntityManager.persist(accountData3);
    }

    @Test
    @Disabled
    public void whenFindByEmail_thenReturnRegisterAccount() {
        RegisterAccount registerAccount = registerAccountRepository.findAccountByEmail("test1@gmail.com").get();

        assertEquals(registerAccount.getName(), "test1");
    }

    @Test
    @Disabled
    public void whenFindByName_thenReturnRegisterAccount() {
        RegisterAccount registerAccount = registerAccountRepository.findAccountByName("test1").get();

        assertEquals(registerAccount.getName(), "test1");
    }

    @Test
    @Disabled
    public void whenFindAccountDataById_thenReturnAccountData() {
        List<AccountData> accountDataList = registerAccountRepository.findAccountDataById(registerAccount.getId());

        assertEquals("ETH", accountDataList.get(1).getGoods());
    }
}
