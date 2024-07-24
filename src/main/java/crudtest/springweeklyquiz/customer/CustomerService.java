package crudtest.springweeklyquiz.customer;

import crudtest.springweeklyquiz.menu.Menu;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("고객을 찾지 못했습니다."));
        return convertToDTO(customer);
    }

    private Customer convertToEntity(CustomerDto customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());
        return customer;
    }

    private CustomerDto convertToDTO(Customer customer) {
        CustomerDto customerDTO = new CustomerDto();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }
}
