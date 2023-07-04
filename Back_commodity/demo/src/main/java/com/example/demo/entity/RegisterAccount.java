package com.example.demo.entity;

import jakarta.persistence.*;
import com.example.demo.entity.AccountData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterAccount {
@Id
@SequenceGenerator(name = "account_sequence",
        sequenceName = "account_sequence",
        allocationSize = 1)
@GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "account_sequence")
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "registerAccount", cascade = CascadeType.ALL)
    private List<AccountData> accountDataList;


}
