package com.example.demo.service;


import com.example.demo.dto.ItemDto;

import java.util.List;

public interface ItemService {
//get put post get delete

 List<ItemDto> getAllItems ();
ItemDto updateItem(ItemDto Item, int id);

ItemDto createItem(ItemDto Item);

ItemDto getItemById(int id);

 void deleteItem(int id);
}
