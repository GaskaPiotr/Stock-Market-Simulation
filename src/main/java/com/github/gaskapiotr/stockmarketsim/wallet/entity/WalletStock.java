package com.github.gaskapiotr.stockmarketsim.wallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WalletStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}
