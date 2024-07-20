package crudtest.springweeklyquiz.order;

import crudtest.springweeklyquiz.Menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private Long id;

    private Order order;

    private Menu menu;

    private int quantity;
}
