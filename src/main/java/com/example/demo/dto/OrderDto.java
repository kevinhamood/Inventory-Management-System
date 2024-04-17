package com.example.demo.dto;



import lombok.Data;
import jakarta.validation.constraints.NotNull;
@Data
public class OrderDto {


    private Integer id;


    @NotNull
    private String name;




    private int boxSize;



}
