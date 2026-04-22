package com.github.gaskapiotr.stockmarketsim.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BankStock {
    @Id
    private String name;
    private int quantity;
}
