package com.task.Service;

import com.task.Repository.EmployeeRepository;
import com.task.Repository.ManagerRepository;
import com.task.Repository.ManagerReviewRepository;
import com.task.dto.ManagerReviewDTO;
import com.task.entites.Employee;
import com.task.entites.Manager;
import com.task.entites.ManagerReview;
import com.task.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerReviewService {

    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;
    private final ManagerReviewRepository managerReviewRepository;


    public void saveManagerReview(ManagerReviewDTO managerReviewDTO) {
        // Fetch the Employee entity
        Employee employee = employeeRepository.findById(managerReviewDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+managerReviewDTO.getEmployeeId()));

        // Fetch the Manager entity
        Manager manager = managerRepository.findById(managerReviewDTO.getManagerId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: "+managerReviewDTO.getManagerId()));

        // Create ManagerReview
        ManagerReview managerReview = new ManagerReview();
        managerReview.setReview(managerReviewDTO.getReview());
        managerReview.setRating(managerReviewDTO.getRating());
        managerReview.setEmployee(employee); // Set the employee
        managerReview.setManager(manager);   // Set the manager

        // Save the ManagerReview
        managerReviewRepository.save(managerReview);
    }
}
