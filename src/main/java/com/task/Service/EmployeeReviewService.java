package com.task.Service;


import com.task.Repository.EmployeeRepository;
import com.task.Repository.EmployeeReviewRepository;
import com.task.dto.EmployeeReviewDTO;
import com.task.entites.Employee;
import com.task.entites.EmployeeReview;
import com.task.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeReviewService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeReviewRepository employeeReviewRepository;

    public void saveEmployeeReview(EmployeeReviewDTO employeeReviewDTO) {

        Employee employee = employeeRepository.findById(employeeReviewDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+employeeReviewDTO.getEmployeeId()));

        EmployeeReview employeeReview = new EmployeeReview();
        employeeReview.setReview(employeeReviewDTO.getReview());
        employeeReview.setEmployee(employee);

        employeeReviewRepository.save(employeeReview);
    }
}
