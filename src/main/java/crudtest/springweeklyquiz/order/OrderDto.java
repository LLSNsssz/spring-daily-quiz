package crudtest.springweeklyquiz.order;

import crudtest.springweeklyquiz.customer.CustomerDto;
import crudtest.springweeklyquiz.store.StoreDto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

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
    private String status;
    private BigDecimal totalAmount;
    private List<OrderItemDto> orderItems;
}