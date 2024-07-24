package crudtest.springweeklyquiz.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
}