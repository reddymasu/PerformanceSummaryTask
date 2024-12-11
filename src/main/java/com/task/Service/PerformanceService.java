package com.task.Service;

import com.task.Repository.EmployeeRepository;
import com.task.Repository.EmployeeReviewRepository;
import com.task.Repository.ManagerReviewRepository;
import com.task.dto.PerformanceSummarydto;
import com.task.entites.Employee;
import com.task.entites.EmployeeReview;
import com.task.entites.ManagerReview;
import com.task.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final EmployeeReviewRepository employeeReviewRepository;
    private final ManagerReviewRepository managerReviewRepository;

    public PerformanceSummarydto getPerformanceSummary(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+employeeId));

        EmployeeReview selfReview = employeeReviewRepository.findTopByEmployeeIdOrderBySubmittedAtDesc(employeeId);
        ManagerReview managerReview = managerReviewRepository.findTopByEmployeeIdOrderBySubmittedAtDesc(employeeId);

        PerformanceSummarydto performanceSummary = new PerformanceSummarydto();
        performanceSummary.setEmployeeName(employee.getName());
        performanceSummary.setSelfReviewText(selfReview != null ? selfReview.getReview() : "No self-review submitted.");
        performanceSummary.setSelfReviewTimestamp(selfReview != null ? selfReview.getSubmittedAt() : null);
        performanceSummary.setManagerReviewText(managerReview != null ? managerReview.getReview() : "No manager review submitted.");
        performanceSummary.setManagerRating(managerReview != null ? managerReview.getRating() : 0);
        performanceSummary.setManagerReviewTimestamp(managerReview != null ? managerReview.getSubmittedAt() : null);

        return performanceSummary;
    }

    public double getPerformanceScore(Long employeeId) {

        PerformanceSummarydto performanceSummarydto=getPerformanceSummary(employeeId);

        Double performancescore= 0.0;
        String employeereview = performanceSummarydto.getSelfReviewText();
        String managerreview= performanceSummarydto.getManagerReviewText();
        int selfreview=0;
        int weight=0;
        int managerrating=performanceSummarydto.getManagerRating();
        if((employeereview.length()>=11 && employeereview.length()<=20) ||
                managerreview.length()>=11 && managerreview.length()<=20) {
            selfreview=1;
            weight=1;
        }
        else if((employeereview.length()>=21 && employeereview.length()<=30) ||
                (managerreview.length()>=21 && managerreview.length()<=30)) {
            selfreview=2;
            weight=2;
        }
        else if((employeereview.length()>=31 && employeereview.length()<=40) ||
                (managerreview.length()>=31 && managerreview.length()<=40)) {
            selfreview=3;
            weight=3;
        }
        else if((employeereview.length()>=41 && employeereview.length()<=50) ||
                (managerreview.length()>=41 && managerreview.length()<=50)) {
            selfreview=4;
            weight=4;
        }
        else
        {
            selfreview=5;
            weight=5;
        }
        performancescore= (double) ((selfreview+(managerrating*weight))/6);
        return performancescore;
    }
}
