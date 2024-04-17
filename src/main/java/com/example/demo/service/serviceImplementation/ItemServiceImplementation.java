package com.example.demo.service.serviceImplementation;
import com.example.demo.repository.ItemRepository;
import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Items;
import com.example.demo.service.ItemService;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemServiceImplementation implements ItemService {


    private ItemRepository itemRepository;

    public ItemServiceImplementation(ItemRepository itemRepository){

        this.itemRepository=itemRepository;

    }

    @Override
    public List<ItemDto> getAllItems(){


        List<Items> items= itemRepository.findAll();
        return items.stream().map(item ->toItemDtoConverter(item)).collect(Collectors.toList());
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, int id) {
        Items item = itemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Item","id",id));

        item.setName(itemDto.getName());
        item.setCategory(itemDto.getCategory());
        Items updatedItem=itemRepository.save(item);

        return toItemDtoConverter(updatedItem);
    }




    @Override
    public ItemDto createItem (ItemDto itemDto){
        Items item = toItemEntityConverter(itemDto);
        Items newItem=itemRepository.save(item);


        ItemDto itemResponse = toItemDtoConverter(newItem);
        return itemResponse;
    }



    @Override
    public ItemDto getItemById(int id){
        Items item=itemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Item","id",id));
        return toItemDtoConverter(item);
    }


    @Override
    public void deleteItem(int id){
        Items item =itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item","id",id));
        itemRepository.delete(item);
    }
//    @Override
//   public List<ItemDto> getAllItems (){
//List<ItemDto> h;
//     return h;
//    }



public ItemDto toItemDtoConverter(Items item){
        ItemDto itemDto=new ItemDto();
    itemDto.setId(item.getId());
    itemDto.setName(item.getName());
    itemDto.setCategory(item.getCategory());
return itemDto;
}

    public Items toItemEntityConverter(ItemDto itemDto){
        Items item =new Items();
        item.setName(itemDto.getName());
        item.setCategory(itemDto.getCategory());

return item;
    }
}
