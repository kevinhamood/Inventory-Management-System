package com.example.demo.controller;



import com.example.demo.service.WareHouseService;
import com.example.demo.dto.WareHouseDto;
import com.example.demo.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/Inventory/WareHouse")
public class WareHouseController {
    private final Logger log = LoggerFactory.getLogger(WareHouseController.class);

    private WareHouseService wareHouseService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public  WareHouseController(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    @GetMapping
    public ResponseEntity<List<WareHouseDto>> getAllWareHouses() {
        return ResponseEntity.ok().body(wareHouseService.getAllWareHouses()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{name}")
    public ResponseEntity<WareHouseDto> getWareHouseByName(
            @PathVariable(name = "name") String name) {
        return ResponseEntity.ok(wareHouseService.getWareHouseByName(name));
    }


//    /**
//     * When Spring Boot finds an argument annotated with @Valid,
//     * it automatically bootstraps the default JSR 380 implementation — Hibernate Validator —
//     * and validates the argument (JSR 380 is a specification of the Java API
//     * for bean validation, part of Jakarta EE and JavaSE.
//     * This ensures that the properties of a bean meet specific criteria,
//     * using annotations such as @NotNull, @Min, and @Max.;
//     * <dependency>     <groupId>org.hibernate.validator</groupId>
//     * <artifactId>hibernate-validator</artifactId>     <version>8.0.0.Final</version> </dependency>).
//     * When the target argument fails to pass the validation,
//     * Spring Boot throws a MethodArgumentNotValidException exception.
//     *
//     * @param ManagerDto
//     * @return
//     */


    @PutMapping("/{name}")
    public ResponseEntity<WareHouseDto> updateWareHouse
            (@Valid @RequestBody WareHouseDto wareHouseDto
                    , @PathVariable(name = "name") String name) {
        return new ResponseEntity<>(wareHouseService.updateWareHouse(wareHouseDto, name), HttpStatus.OK);
    }



}
