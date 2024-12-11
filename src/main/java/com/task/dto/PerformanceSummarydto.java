package com.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceSummarydto {

    private String employeeName;
    private String selfReviewText;
    private Date selfReviewTimestamp;
    private String managerReviewText;
    private int managerRating;
    private Date managerReviewTimestamp;
}
