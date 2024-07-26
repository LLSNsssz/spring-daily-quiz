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
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long id) {
        OrderDto orderById = orderService.getOrderById(id);
        return ResponseEntity.ok(orderById);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<OrderDto>> getAllOrderByStoreId(@PathVariable("id") Long id) {
        List<OrderDto> allOrderByStoreId = orderService.getAllOrderByStoreId(id);
        return ResponseEntity.ok(allOrderByStoreId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable("id") Long id, @RequestBody OrderDto orderDto) {
        OrderDto updatedOrderDto = orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok(updatedOrderDto);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> completeOrderById(@PathVariable("id") Long id) {
        orderService.completeOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrderById(@PathVariable("id") Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

}
