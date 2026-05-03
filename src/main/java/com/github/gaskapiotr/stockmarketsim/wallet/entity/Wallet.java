package com.github.gaskapiotr.stockmarketsim.wallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wallet {
    @Id
    private String id;
    @OneToMany(mappedBy="wallet")
    private List<WalletStock> stocks;
}
