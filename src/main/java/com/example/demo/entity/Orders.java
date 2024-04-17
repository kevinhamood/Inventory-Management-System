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

public class Orders {
    @Id
    @GeneratedValue(
            strategy =GenerationType.IDENTITY
    )
    private Integer id;


    @Column
    private String name;



    @Column
    private int boxSize;







}
