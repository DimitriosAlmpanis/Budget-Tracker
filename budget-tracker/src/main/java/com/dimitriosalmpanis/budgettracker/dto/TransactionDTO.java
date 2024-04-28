package com.dimitriosalmpanis.budgettracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private double amount;
    private Timestamp date;
    private String description;
    private CategoryDTO category;
}
