package com.github.gaskapiotr.stockmarketsim.log.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock_log")
@Getter
@Setter
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // TODO change Long to lower number
    private Long id;
    private String type;
    private String wallet_id;
    private String stock_name;
}
