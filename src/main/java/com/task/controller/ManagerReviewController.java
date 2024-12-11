package com.task.controller;

import com.task.Service.ManagerReviewService;
import com.task.dto.ManagerReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager-reviews")
public class ManagerReviewController {

    private final ManagerReviewService managerReviewService;

    @PostMapping
    public ResponseEntity<String> saveManagerReview(@RequestBody ManagerReviewDTO managerReviewDTO) {
        managerReviewService.saveManagerReview(managerReviewDTO);
        return ResponseEntity.ok("Manager review submitted successfully.");
    }


}
