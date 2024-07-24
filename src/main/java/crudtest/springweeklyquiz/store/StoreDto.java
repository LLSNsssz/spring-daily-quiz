package crudtest.springweeklyquiz.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link crudtest.springweeklyquiz.store.Store}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
}