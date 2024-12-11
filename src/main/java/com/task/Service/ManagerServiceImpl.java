package com.task.Service;

import com.task.Repository.ManagerRepository;
import com.task.dto.ManagerDTO;
import com.task.entites.Employee;
import com.task.entites.Manager;
import com.task.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final ManagerRepository managerRepository;
    private final ModelMapper modelMapper;

    public ManagerDTO createManager(ManagerDTO managerDTO) {

        List<Manager> managerList= managerRepository.findByEmail(managerDTO.getEmail());
        if(!managerList.isEmpty())
        {
            throw new RuntimeException("Manager already exists with email: " + managerDTO.getEmail());
        }
        Manager manager = modelMapper.map(managerDTO, Manager.class); // Convert DTO to entity
        Manager savedManager = managerRepository.save(manager);
        return modelMapper.map(savedManager, ManagerDTO.class); // Convert entity to DTO
    }

    public Manager getManagerById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + id));
    }


}
