package com.example.demo.service;



import com.example.demo.dto.ManagerDto;

import java.util.List;

public interface ManagerService {
//get put post get delete

    List<ManagerDto> getAllManagers ();




    ManagerDto updateManager(ManagerDto Manager, int id);

    ManagerDto addNewManager(ManagerDto Manager);

    ManagerDto getManagerById(int id);

    void deleteManager(int id);
}
