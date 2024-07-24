package crudtest.springweeklyquiz.order;

import crudtest.springweeklyquiz.customer.CustomerRepository;
import crudtest.springweeklyquiz.menu.MenuRepository;
import crudtest.springweeklyquiz.order.oderItem.OrderItem;
import crudtest.springweeklyquiz.order.oderItem.OrderItemDto;
import crudtest.springweeklyquiz.store.Store;
import crudtest.springweeklyquiz.store.StoreRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public OrderService(OrderRepository orderRepository,
        CustomerRepository customerRepository, StoreRepository storeRepository,
        MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.menuRepository = menuRepository;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = convertToEntity(orderDto);
        order.setStatus(OrderStatus.RECEIVED);
        order.setTotalAmount(calculateTotalAmount(order.getOrderItems()));
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    public OrderDto updateOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다."));
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    public void cancelOrder(Long id) {
        updateOrderStatus(id, OrderStatus.CANCELED);
    }

    public void completeOrder(Long id) {
        updateOrderStatus(id, OrderStatus.COMPLETED);
    }

    private void updateOrderStatus(Long id, OrderStatus orderStatus) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다."));
        order.setStatus(orderStatus);
        orderRepository.save(order);
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다."));
        return convertToDTO(order);
    }

    public List<OrderDto> getAllOrderByStoreId(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("가게를 찾지 못했습니다."));
        List<Order> byStoreId = orderRepository.findByStoreId(store.getId());
        return byStoreId.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream().map(item -> item.getPrice().multiply(
                BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomer(customerRepository.findById(orderDto.getCustomerId()).orElseThrow(() -> new IllegalArgumentException("고객을 찾지 못했습니다.")));
        order.setStore(storeRepository.findById(orderDto.getStoreId()).orElseThrow(() -> new IllegalArgumentException("가게을 찾지 못했습니다.")));
        order.setStatus(orderDto.getStatus());
        order.setOrderDate(orderDto.getOrderDate());
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
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setOrderItems(order.getOrderItems().stream().map(this::convertToDTO).collect(Collectors.toList()));
        return orderDto;
    }

    private OrderItem convertToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setOrder(orderRepository.findById(orderItemDto.getOrderId()).orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다.")));
        orderItem.setMenu(menuRepository.findById(orderItemDto.getMenuId()).orElseThrow(() -> new IllegalArgumentException("메뉴를 찾지 못했습니다.")));
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
