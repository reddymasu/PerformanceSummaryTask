package com.task.dto;


import com.task.entites.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDTO {

    private Long id;
    private String name;
    private String department;
    private String email;
    private List<Employee> employees;
}
