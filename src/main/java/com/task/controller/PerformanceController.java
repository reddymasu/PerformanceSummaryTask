package com.task.controller;

import com.task.Service.PerformanceService;
import com.task.dto.PerformanceSummarydto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    // Endpoint to get performance summary of an employee
    @GetMapping("/api/employees/{employeeId}/performance-summary")
    public ResponseEntity<PerformanceSummarydto> getPerformanceSummary(@PathVariable Long employeeId) {
        PerformanceSummarydto summary = performanceService.getPerformanceSummary(employeeId);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/api/employees/{employeeId}/performance-score")
    public ResponseEntity<String> getPerformanceScore(@PathVariable Long employeeId) {
        double performanceScore = performanceService.getPerformanceScore(employeeId);
        return ResponseEntity.ok("Performance Score of Employee With Id: "+employeeId+" is: "+performanceScore);
    }




}
