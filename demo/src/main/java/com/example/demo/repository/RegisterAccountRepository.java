package com.example.demo.repository;

import com.example.demo.entity.RegisterAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import com.example.demo.entity.AccountData;

@Repository
public interface RegisterAccountRepository extends JpaRepository<RegisterAccount, Long> {

    @Query("SELECT s FROM RegisterAccount s WHERE s.email=?1")
    Optional<RegisterAccount> findAccountByEmail(String email);


    @Query("SELECT d FROM RegisterAccount d WHERE d.name=?1")
    Optional<RegisterAccount> findAccountByName(String name);

    RegisterAccount findByName(String name);
    RegisterAccount findByPassword(String password);
    RegisterAccount findById(long id);
    @Query("SELECT d FROM AccountData d WHERE d.registerAccount.id = :id")
    List<AccountData> findAccountDataById(Long id);
    
}
