package com.task.Repository;

import com.task.entites.ManagerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerReviewRepository extends JpaRepository<ManagerReview,Long> {
    ManagerReview findTopByEmployeeIdOrderBySubmittedAtDesc(Long employeeId);
}
