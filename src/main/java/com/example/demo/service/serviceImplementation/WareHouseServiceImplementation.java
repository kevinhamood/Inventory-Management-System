package com.example.demo.service.serviceImplementation;
import com.example.demo.repository.WareHouseRepository;
import com.example.demo.dto.WareHouseDto;
import com.example.demo.entity.WareHouse;
import com.example.demo.service.WareHouseService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WareHouseServiceImplementation implements WareHouseService {


    private WareHouseRepository wareHouseRepository;

    public WareHouseServiceImplementation(WareHouseRepository wareHouseRepository){

        this.wareHouseRepository=wareHouseRepository;

    }

    @Override
    public List<WareHouseDto> getAllWareHouses(){


        List<WareHouse> wareHouses= wareHouseRepository.findAll();
        return wareHouses.stream().map(wareHouse ->toWareHouseDtoConverter(wareHouse)).collect(Collectors.toList());
    }

    @Override
    public WareHouseDto updateWareHouse(WareHouseDto wareHouseDto, String name) {
        WareHouse wareHouse = wareHouseRepository.findById(name).orElseThrow(()->new ResourceNotFoundException("WareHouse","name",name));

        wareHouse.setName(wareHouseDto.getName());
        wareHouse.setAddress(wareHouseDto.getAddress());
        wareHouse.setSize(wareHouseDto.getSize());
        WareHouse updatedWareHouse=wareHouseRepository.save(wareHouse);

        return toWareHouseDtoConverter(updatedWareHouse);
    }








    @Override
    public WareHouseDto getWareHouseByName(String name){
        WareHouse wareHouse=wareHouseRepository.findById(name).orElseThrow(()->new ResourceNotFoundException("WareHouse","name",name));
        return toWareHouseDtoConverter(wareHouse);
    }







    public WareHouseDto toWareHouseDtoConverter(WareHouse wareHouse){
        WareHouseDto wareHouseDto=new WareHouseDto();
        wareHouseDto.setName(wareHouse.getName());
        wareHouseDto.setAddress(wareHouse.getAddress());
        wareHouseDto.setSize(wareHouse.getSize());

        return wareHouseDto;
    }

    public WareHouse toWareHouseEntityConverter(WareHouseDto wareHouseDto){
        WareHouse wareHouse =new WareHouse();
        wareHouse.setName(wareHouseDto.getName());
        wareHouse.setAddress(wareHouseDto.getAddress());
        wareHouse.setSize(wareHouseDto.getSize());

        return wareHouse;
    }
}
