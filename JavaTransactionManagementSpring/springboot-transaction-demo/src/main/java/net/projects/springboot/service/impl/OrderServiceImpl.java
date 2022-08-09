package net.projects.springboot.service.impl;

import net.projects.springboot.dto.OrderRequest;
import net.projects.springboot.dto.OrderResponse;
import net.projects.springboot.entity.Order;
import net.projects.springboot.entity.Payment;
import net.projects.springboot.exception.PaymentException;
import net.projects.springboot.repository.OrderRepository;
import net.projects.springboot.repository.PaymentRepository;
import net.projects.springboot.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        //Luam OrderRequest din DTO si il punem intr-un obiect Order
        Order order = orderRequest.getOrder();
        //Setam statusul obiectului
        order.setStatus("INPROGRESS");
        //Setam tracking number
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        //Salvam obiectul in baza de date
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();
        //Magazinul nostru suporta doar comenzi facute cu cardul DEBIT
        if (!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Payment card type not supported");
        }
        //Salvam obiectul in baza de date
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }
}
