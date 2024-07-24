package crudtest.springweeklyquiz.menu;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

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