package crudtest.springweeklyquiz.order;

import crudtest.springweeklyquiz.order.oderItem.OrderItemDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link Order}
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long customerId;
    private Long storeId;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private List<OrderItemDto> orderItems;
}