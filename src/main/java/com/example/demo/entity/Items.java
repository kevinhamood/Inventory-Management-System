package com.example.demo.entity;


import lombok.*;
import jakarta.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
        (
        name="items" ,uniqueConstraints = {@UniqueConstraint(columnNames={"name"})}

)

public class Items {
@Id
    @GeneratedValue(
            strategy =GenerationType.IDENTITY
    )
    private Integer id;


@Column(name="name" , nullable=false)
    private String name;

@Column
    private String category;


}
