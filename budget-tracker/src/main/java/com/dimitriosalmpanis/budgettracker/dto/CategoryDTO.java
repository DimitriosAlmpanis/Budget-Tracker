package com.dimitriosalmpanis.budgettracker.dto;

import com.dimitriosalmpanis.budgettracker.entity.Transaction;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
}
