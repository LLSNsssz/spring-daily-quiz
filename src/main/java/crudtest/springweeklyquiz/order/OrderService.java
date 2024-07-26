package crudtest.springweeklyquiz.order;

import crudtest.springweeklyquiz.customer.CustomerRepository;
import crudtest.springweeklyquiz.menu.Menu;
import crudtest.springweeklyquiz.menu.MenuRepository;
import crudtest.springweeklyquiz.order.oderItem.OrderItem;
import crudtest.springweeklyquiz.order.oderItem.OrderItemDto;
import crudtest.springweeklyquiz.order.oderItem.OrderItemRepository;
import crudtest.springweeklyquiz.store.Store;
import crudtest.springweeklyquiz.store.StoreRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
        CustomerRepository customerRepository, StoreRepository storeRepository,
        MenuRepository menuRepository,
        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.menuRepository = menuRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = convertToEntity(orderDto);
        order.setStatus(OrderStatus.RECEIVED);
        order.setOrderDate(LocalDateTime.now());
        calculateAndSetTotalAmount(order);
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    @Transactional
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다."));

        // 주문 항목 업데이트
        Order finalOrder = order;
        List<OrderItem> newOrderItems = orderDto.getOrderItems().stream()
            .map(itemDto -> {
                OrderItem item = new OrderItem();
                item.setOrder(finalOrder);
                item.setMenu(menuRepository.findById(itemDto.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾지 못했습니다.")));
                item.setQuantity(itemDto.getQuantity());
                return item;
            })
            .collect(Collectors.toList());
        order.setOrderItems(newOrderItems);

        // 총 주문 금액 재계산
        ;
        order.setTotalAmount(calculateAndSetTotalAmount(finalOrder));

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

    private Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomer(customerRepository.findById(orderDto.getCustomerId())
            .orElseThrow(() -> new IllegalArgumentException("고객을 찾지 못했습니다.")));
        order.setStore(storeRepository.findById(orderDto.getStoreId())
            .orElseThrow(() -> new IllegalArgumentException("가게를 찾지 못했습니다.")));
        order.setStatus(orderDto.getStatus());
        order.setOrderDate(orderDto.getOrderDate());

        List<OrderItem> orderItems = orderDto.getOrderItems().stream()
            .map(itemDto -> {
                OrderItem item = new OrderItem();
                Menu menu = menuRepository.findById(itemDto.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾지 못했습니다."));
                item.setMenu(menu);
                item.setQuantity(itemDto.getQuantity());
                item.setOrder(order);
                return item;
            })
            .collect(Collectors.toList());

        order.setOrderItems(orderItems);
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
        return orderItem;
    }

    private OrderItemDto convertToDTO(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderId(orderItem.getOrder().getId());
        orderItemDto.setMenuId(orderItem.getMenu().getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }

    private BigDecimal calculateAndSetTotalAmount(Order order) {
        BigDecimal totalAmount = order.getOrderItems().stream()
            .map(item -> item.getMenu().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);
        return totalAmount;
    }

}

