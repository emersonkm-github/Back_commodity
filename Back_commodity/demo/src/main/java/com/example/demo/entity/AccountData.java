package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class AccountData {
    @Id
    @SequenceGenerator(name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence")
    private Long id;
    private String goods;
    
    private Double amount;
    
    @ManyToOne
    @JoinColumn(name = "register_account_id")
    private RegisterAccount registerAccount;
    

    


    public AccountData() {
    }

    public AccountData(Long id, String goods, Double amount) {
        this.id = id;
        this.goods = goods;
        this.amount = amount;
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setRegisterAccount(RegisterAccount registerAccount) {
        this.registerAccount = registerAccount;
    }
    
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    

}
