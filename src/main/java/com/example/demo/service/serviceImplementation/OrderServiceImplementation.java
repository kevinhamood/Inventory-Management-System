package com.example.demo.service.serviceImplementation;
import com.example.demo.repository.OrderRepository;
import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImplementation implements OrderService {


    private OrderRepository orderRepository;

    public OrderServiceImplementation(OrderRepository orderRepository){

        this.orderRepository=orderRepository;

    }

    @Override
    public List<OrderDto> getAllOrders(){


        List<Orders> orders= orderRepository.findAll();
        return orders.stream().map(order ->toOrderDtoConverter(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, int id) {
        Orders order = orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","id",id));

        order.setName(orderDto.getName());
        Orders updatedOrder=orderRepository.save(order);

        return toOrderDtoConverter(updatedOrder);
    }




    @Override
    public OrderDto createOrder (OrderDto orderDto){
        Orders order = toOrderEntityConverter(orderDto);
        Orders newOrder=orderRepository.save(order);


        OrderDto orderResponse = toOrderDtoConverter(newOrder);
        return orderResponse;
    }



    @Override
    public OrderDto getOrderById(int id){
        Orders order=orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","id",id));
        return toOrderDtoConverter(order);
    }


    @Override
    public void deleteOrder(int id){
        Orders order =orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order","id",id));
        orderRepository.delete(order);
    }




    public OrderDto toOrderDtoConverter(Orders order){
        OrderDto orderDto=new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setName(order.getName());
        orderDto.setBoxSize(order.getBoxSize());
        return orderDto;
    }

    public Orders toOrderEntityConverter(OrderDto orderDto){
        Orders order =new Orders();
        order.setName(orderDto.getName());
        order.setBoxSize(orderDto.getBoxSize());

        return order;
    }
}
