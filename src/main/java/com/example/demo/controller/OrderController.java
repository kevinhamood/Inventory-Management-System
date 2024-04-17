package com.example.demo.controller;



import com.example.demo.service.OrderService;
import com.example.demo.dto.OrderDto;
import com.example.demo.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/Inventory/Order")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);


    private OrderService orderService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public  OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(
            @PathVariable(name = "id") int id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
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
    public ResponseEntity<OrderDto> createOrder
            (@Valid @RequestBody OrderDto orderDto) {
        if (orderDto.getId() !=null) {
            log.error("Cannot have an ID {}", orderDto);
            throw new BadRequestException(OrderService.class.getSimpleName(),
                    "Id");
        }
        if (orderDto.getName() ==null) {
            log.error("Cannot have an name {}", orderDto);
            throw new BadRequestException(OrderService.class.getSimpleName(),
                    "Name");
        }
        return new ResponseEntity(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder
            (@Valid @RequestBody OrderDto orderDto
                    , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(orderService.updateOrder(orderDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {
        orderService.deleteOrder(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
