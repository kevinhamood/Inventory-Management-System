package com.example.demo.controller;

import com.example.demo.service.ManagerService;
import com.example.demo.dto.ManagerDto;
import com.example.demo.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/Inventory/Manager")
public class ManagerController {
    private final Logger log = LoggerFactory.getLogger(ManagerController.class);


    private ManagerService managerService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public  ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public ResponseEntity<List<ManagerDto>> getAllManagers() {
        return ResponseEntity.ok().body(managerService.getAllManagers()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> getManagerById(
            @PathVariable(name = "id") int id) {
        return ResponseEntity.ok(managerService.getManagerById(id));
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


    @PostMapping
    public ResponseEntity<ManagerDto> addNewManager
    (@Valid @RequestBody ManagerDto managerDto) {
        if (managerDto.getId() !=null) {
            log.error("Cannot have an ID {}", managerDto);
            throw new BadRequestException(ManagerService.class.getSimpleName(),
                    "Id");
        }
        if (managerDto.getName() ==null) {
            log.error("Cannot have an name {}", managerDto);
            throw new BadRequestException(ManagerService.class.getSimpleName(),
                    "Name");
        }
        return new ResponseEntity(managerService.addNewManager(managerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerDto> updateManager
            (@Valid @RequestBody ManagerDto managerDto
                    , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(managerService.updateManager(managerDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable(name = "id") int id) {
        managerService.deleteManager(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
