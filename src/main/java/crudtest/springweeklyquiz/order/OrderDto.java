package crudtest.springweeklyquiz.order;

import crudtest.springweeklyquiz.Customer;
import crudtest.springweeklyquiz.Store;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Customer customer;
    private String status;
    private LocalDateTime orderDate;
    private List<OrderItem> orderItems;
    private BigDecimal totalPrice;

    public Order toEntity() {
        return new Order(
            this.id,
            this.customer,
            this.status,
            this.orderDate,
            this.orderItems,
            this.totalPrice);
    }

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
            .id(order.getId())
            .customer(order.getCustomer())
            .status(order.getStatus())
            .orderDate(order.getOrderDate())
            .orderItems(order.getOrderItems())
            .totalPrice(order.getTotalPrice())
            .build();
    }
}
