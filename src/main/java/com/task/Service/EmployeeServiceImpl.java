package com.task.Service;


import com.task.dto.EmployeeDTO;
import com.task.Repository.EmployeeRepository;
import com.task.Repository.ManagerRepository;
import com.task.entites.Employee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmplyeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;



    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        List<Employee> employeeList= employeeRepository.findByEmail(employeeDTO.getEmail());
        if(!employeeList.isEmpty())
        {
            throw new RuntimeException("Employee already exists with email: " + employeeDTO.getEmail());
        }

        Employee newEmployee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(newEmployee);

        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }





}
