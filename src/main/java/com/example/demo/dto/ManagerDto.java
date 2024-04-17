package com.example.demo.dto;



import lombok.Data;
import jakarta.validation.constraints.NotNull;
@Data
public class ManagerDto {
    private Integer id;


    @NotNull
    private String name;

    private String phoneNumber;



    private String email;

    private int age;


}
