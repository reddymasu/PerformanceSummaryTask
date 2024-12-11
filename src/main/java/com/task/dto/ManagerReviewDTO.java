package com.task.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerReviewDTO {

    private String review;
    private int rating;
    private Long employeeId;
    private Long managerId;
}
