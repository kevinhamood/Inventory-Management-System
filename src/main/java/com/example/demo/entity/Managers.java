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
        (
                name="Manager" ,uniqueConstraints = {@UniqueConstraint(columnNames={"name"})}

        )

public class Managers {
    @Id
    @GeneratedValue(
            strategy =GenerationType.IDENTITY
    )
    private Integer id;


    @Column(name="name", nullable=false)
    private String name;

    @Column
    private String phoneNumber;


    @Column
    private String email;


    @Column
    private int age;


}
