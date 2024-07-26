package crudtest.springweeklyquiz.menu;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link Menu}
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private String description;
}