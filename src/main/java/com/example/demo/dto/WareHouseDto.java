package com.example.demo.dto;



import lombok.Data;
import jakarta.validation.constraints.NotNull;
@Data
public class WareHouseDto {


    @NotNull

    private String name;


    private String address;

    private int size;




}
