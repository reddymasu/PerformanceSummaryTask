package com.task.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReviewDTO {

    private String review;
    private Long employeeId;
}
