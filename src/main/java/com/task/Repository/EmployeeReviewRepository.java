package com.task.Repository;

import com.task.entites.EmployeeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReviewRepository extends JpaRepository<EmployeeReview, Long> {
    EmployeeReview findTopByEmployeeIdOrderBySubmittedAtDesc(Long employeeId);
}
