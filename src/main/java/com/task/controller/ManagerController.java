package com.task.controller;

import com.task.Service.ManagerService;
import com.task.dto.EmployeeDTO;
import com.task.Service.EmplyeeService;
import com.task.dto.ManagerDTO;
import com.task.entites.Manager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/managers")
public class ManagerController {

    private final EmplyeeService emplyeeService;
    private final ManagerService managerService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ManagerDTO> createManager(@RequestBody ManagerDTO managerDTO) {
        ManagerDTO createdManagerDTO = managerService.createManager(managerDTO);
        return ResponseEntity.ok(createdManagerDTO);
    }

    @PostMapping("/{managerId}/employees")
    public ResponseEntity<EmployeeDTO> addEmployeeToManager(@PathVariable Long managerId, @RequestBody EmployeeDTO employeeDTO) {
        Manager manager = managerService.getManagerById(managerId);
        employeeDTO.setManagerId(manager.getId()); // Set manager ID in the employee DTO
        EmployeeDTO savedEmployeeDTO = emplyeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @GetMapping("/{managerId}/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByManager(@PathVariable Long managerId) {
        Manager manager = managerService.getManagerById(managerId);
        List<EmployeeDTO> employeeDTOs = manager.getEmployees()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class)) // Convert entities to DTOs
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTOs);
    }

    @GetMapping("/{managerId}")
    public ResponseEntity<ManagerDTO> getManagerById(@PathVariable Long managerId) {
        Manager manager = managerService.getManagerById(managerId);
        ManagerDTO managerDTO = modelMapper.map(manager, ManagerDTO.class); // Convert entity to DTO
        return ResponseEntity.ok(managerDTO);
    }


}
