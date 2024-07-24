package crudtest.springweeklyquiz.customer;

import org.springframework.stereotype.Service;

/**
 * 고객 관리를 위한 서비스 클래스 입니다.
 * 이 클래스는 고객 정보의 CRUD 연산을 처리합니다
 */
@Service
public class CustomerService {

    // 고객 정보를 데이터베이스에서 관리하기 위한 레포지토리
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * 새로운 고객을 추가 합니다.
     *
     * @param customerDto 추가할 고객의 정보를 담은 DTO
     * @return 추가된 고객의 정보를 담은 DTO
     */
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }

    /**
     * 기존 고객의 정보를 업데이트 합니다.
     *
     * @param id 업테이트할 고객의 ID
     * @return 업데이트된 고객의 정보를 담은 DTO
     */
    public CustomerDto updateCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("고객을 찾지 못했습니다."));
        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }

    /**
     * 지정된 ID 의 고객 정보를 삭제 합니다.
     *
     * @param id 삭제할 고객의 ID
     */
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    /**
     * 지정된 ID 의 고객 정보를 조회합니다.
     * @param id 조회할 고객의 ID
     * @return 조회된 고객 정보를 담은 DTO
     * @throws IllegalArgumentException 지정된 ID의 고객이 존재하지 않을 경우
     */
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
