package com.example.demo.service;




import com.example.demo.dto.WareHouseDto;

import java.util.List;

public interface WareHouseService {
//get put  get

    List<WareHouseDto> getAllWareHouses ();



    WareHouseDto updateWareHouse(WareHouseDto WareHouseDto, String name);


    WareHouseDto getWareHouseByName( String name);

}
