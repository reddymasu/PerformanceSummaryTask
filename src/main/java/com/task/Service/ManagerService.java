package com.task.Service;

import com.task.dto.ManagerDTO;
import com.task.entites.Manager;

public interface ManagerService {
    ManagerDTO createManager(ManagerDTO managerDTO);

    Manager getManagerById(Long managerId);
}
