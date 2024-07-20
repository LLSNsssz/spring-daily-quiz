package crudtest.springweeklyquiz.order;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderDto.toEntity();
        Order savedBook = orderRepository.save(order);
        return OrderDto.toDto(savedBook);
    }
}
