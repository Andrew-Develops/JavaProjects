package net.projects.springboot.dto;

import lombok.Data;
import lombok.Setter;
import net.projects.springboot.entity.Order;
import net.projects.springboot.entity.Payment;

@Data
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
