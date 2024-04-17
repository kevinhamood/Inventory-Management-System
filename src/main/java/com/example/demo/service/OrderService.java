package com.example.demo.service;



import com.example.demo.dto.OrderDto;

import java.util.List;

public interface OrderService {
//get put post get delete

    List<OrderDto> getAllOrders ();



    OrderDto updateOrder(OrderDto Order, int id);

    OrderDto createOrder(OrderDto Order);

    OrderDto getOrderById(int id);

    void deleteOrder(int id);
}
