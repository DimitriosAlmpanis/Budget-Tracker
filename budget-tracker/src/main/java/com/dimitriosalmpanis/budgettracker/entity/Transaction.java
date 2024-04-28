package com.dimitriosalmpanis.budgettracker.entity;

import com.dimitriosalmpanis.budgettracker.dto.CategoryDTO;
import jakarta.persistence.*;

import lombok.*;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private Timestamp date;

    private String description;

    @ManyToOne
    private Category category;
}