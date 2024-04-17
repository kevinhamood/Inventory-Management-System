package com.example.demo.entity;


import lombok.Data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table

public class WareHouse {



    @Id
    @Column
    private String name;



    @Column
    private String address;

    @Column
    private int size;




}
