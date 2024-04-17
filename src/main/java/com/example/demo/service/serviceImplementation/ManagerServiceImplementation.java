package com.example.demo.service.serviceImplementation;


import com.example.demo.dto.ManagerDto;
import com.example.demo.entity.Managers;
import com.example.demo.repository.ManagerRepository;

import com.example.demo.service.ManagerService;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ManagerServiceImplementation implements ManagerService {


    private ManagerRepository managerRepository;

    public ManagerServiceImplementation(ManagerRepository managerRepository){

        this.managerRepository=managerRepository;

    }

    @Override
    public List<ManagerDto> getAllManagers(){


        List<Managers> managers= managerRepository.findAll();
        return managers.stream().map(manager ->toManagerDtoConverter(manager)).collect(Collectors.toList());
    }

    @Override
    public ManagerDto updateManager(ManagerDto managerDto, int id) {
        Managers manager = managerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Manager","id",id));

        manager.setName(managerDto.getName());
        manager.setPhoneNumber(managerDto.getPhoneNumber());
        manager.setEmail(managerDto.getEmail());
        manager.setAge(managerDto.getAge());
        Managers updatedManager=managerRepository.save(manager);
        return toManagerDtoConverter(updatedManager);
    }




    @Override
    public ManagerDto addNewManager (ManagerDto managerDto){
        Managers manager = toManagerEntityConverter(managerDto);
        Managers newManager=managerRepository.save(manager);


        ManagerDto managerResponse = toManagerDtoConverter(newManager);
        return managerResponse;
    }



    @Override
    public ManagerDto getManagerById(int id){
        Managers manager=managerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Manager","id",id));
        return toManagerDtoConverter(manager);
    }


    @Override
    public void deleteManager(int id){
        Managers manager =managerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Manager","id",id));
        managerRepository.delete(manager);
    }
//    @Override
//   public List<ItemDto> getAllItems (){
//List<ItemDto> h;
//     return h;
//    }



    public ManagerDto toManagerDtoConverter(Managers manager){
        ManagerDto managerDto=new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setName(manager.getName());
        managerDto.setEmail(manager.getEmail());
        managerDto.setPhoneNumber(manager.getPhoneNumber());
        managerDto.setAge(manager.getAge());
        return managerDto;
    }

    public Managers toManagerEntityConverter(ManagerDto managerDto){
        Managers manager =new Managers();
        manager.setName(managerDto.getName());
        manager.setEmail(managerDto.getEmail());
        manager.setPhoneNumber(managerDto.getPhoneNumber());
        manager.setAge(managerDto.getAge());

        return manager;
    }
}
