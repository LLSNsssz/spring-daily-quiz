package crudtest.springweeklyquiz.order;

import crudtest.springweeklyquiz.customer.Customer;
import crudtest.springweeklyquiz.menu.Menu;
import crudtest.springweeklyquiz.store.Store;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = convertToEntity(orderDto);
        order.setStatus("접수");
        order.setTotalAmount(calculateTotalAmount(order.getOrderItems()));
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = convertToEntity(orderDto);
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다."));
        order.setStatus("취소");
        orderRepository.save(order);
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다."));
        return convertToDTO(order);
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream().map(item -> item.getPrice().multiply(
                BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomer(new Customer());
        order.getCustomer().setId(orderDto.getCustomerId());
        order.setStore(new Store());
        order.getStore().setId(orderDto.getStoreId());
        order.setStatus(orderDto.getStatus());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setOrderItems(orderDto.getOrderItems().stream().map(this::convertToEntity).collect(Collectors.toList()));
        return order;
    }

    private OrderDto convertToDTO(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setStoreId(order.getStore().getId());
        orderDto.setStatus(order.getStatus());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setOrderItems(order.getOrderItems().stream().map(this::convertToDTO).collect(Collectors.toList()));
        return orderDto;
    }

    private OrderItem convertToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setOrder(new Order());
        orderItem.getOrder().setId(orderItemDto.getOrderId());
        orderItem.setMenu(new Menu());
        orderItem.getMenu().setId(orderItemDto.getMenuId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }

    private OrderItemDto convertToDTO(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderId(orderItem.getOrder().getId());
        orderItemDto.setMenuId(orderItem.getMenu().getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }
}
