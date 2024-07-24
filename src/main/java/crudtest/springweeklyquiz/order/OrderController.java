package crudtest.springweeklyquiz.order;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto orderById = orderService.getOrderById(id);
        return ResponseEntity.ok(orderById);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<OrderDto>> getAllOrderByStoreId(@PathVariable Long id) {
        List<OrderDto> allOrderByStoreId = orderService.getAllOrderByStoreId(id);
        return ResponseEntity.ok(allOrderByStoreId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable Long id) {
        OrderDto orderDto = orderService.updateOrder(id);
        return ResponseEntity.ok(orderDto);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<OrderDto> completeOrderById(@PathVariable Long id) {
        orderService.completeOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancelOrderById(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

}
