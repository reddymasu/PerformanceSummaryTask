package com.task.controller;


import com.task.Service.EmployeeReviewService;
import com.task.dto.EmployeeReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee-reviews")
@RequiredArgsConstructor
public class EmployeeReviewController {

    private final EmployeeReviewService employeeReviewService;

    @PostMapping
    public ResponseEntity<String> saveEmployeeReview(@RequestBody EmployeeReviewDTO employeeReviewDTO) {
        employeeReviewService.saveEmployeeReview(employeeReviewDTO);
        return ResponseEntity.ok("Employee review submitted successfully.");
    }
}
