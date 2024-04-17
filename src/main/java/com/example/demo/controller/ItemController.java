package com.example.demo.controller;




import com.example.demo.service.ItemService;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/Inventory/Item")
public class ItemController {
    private final Logger log = LoggerFactory.getLogger(ItemController.class);


    private ItemService itemService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public  ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok().body(itemService.getAllItems()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(
            @PathVariable(name = "id") int id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }



    @PostMapping
    public ResponseEntity<ItemDto> createItem
    (@Valid @RequestBody ItemDto itemDto) {
        if (itemDto.getId() !=null) {
            log.error("Cannot have an ID {}", itemDto);
            throw new BadRequestException(ItemService.class.getSimpleName(),
                    "Id");
        }
        if (itemDto.getName() ==null) {
            log.error("Cannot have an name {}", itemDto);
            throw new BadRequestException(ItemService.class.getSimpleName(),
                    "Name");
        }
        return new ResponseEntity(itemService.createItem(itemDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem
            (@Valid @RequestBody ItemDto itemDto
                    , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(itemService.updateItem(itemDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable(name = "id") int id) {
        itemService.deleteItem(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
