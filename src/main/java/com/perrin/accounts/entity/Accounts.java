package com.perrin.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Accounts extends BaseEntity {

    @Id
    private Long accountNumber;

    private String accountType;

    private Long customerId;

    private String branchAddress;

}
