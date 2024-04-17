package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Items;

import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items, Integer> {

}
