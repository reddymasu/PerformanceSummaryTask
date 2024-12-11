package com.task.Repository;

import com.task.entites.Employee;
import com.task.entites.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    List<Manager> findByEmail(String email);
}
