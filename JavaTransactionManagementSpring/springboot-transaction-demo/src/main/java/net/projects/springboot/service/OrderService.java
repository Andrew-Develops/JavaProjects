package net.projects.springboot.service;

import net.projects.springboot.dto.OrderRequest;
import net.projects.springboot.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);
}
