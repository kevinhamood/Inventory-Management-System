package com.example.demo.dto;



import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Data

public class ItemDto {


private Integer id;

   @NotNull
private String name;

private String category;


}
