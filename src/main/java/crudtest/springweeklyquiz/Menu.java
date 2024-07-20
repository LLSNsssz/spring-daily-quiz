package crudtest.springweeklyquiz;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    private Long id;

    private String name;
    private String category;
    private BigDecimal price;
    private String explanation;
}
