package crudtest.springweeklyquiz.store;

import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDto createOrder(StoreDto orderDto) {
        Store store = convertToEntity(orderDto);
        store = storeRepository.save(store);
        return convertToDTO(store);
    }

    public StoreDto updateOrder(StoreDto orderDto) {
        Store store = convertToEntity(orderDto);
        store = storeRepository.save(store);
        return convertToDTO(store);
    }

    public void cancelOrder(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다."));
        storeRepository.save(store);
    }

    public StoreDto getOrderById(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다."));
        return convertToDTO(store);
    }

    private Store convertToEntity(StoreDto storeDto) {
        Store store = new Store();
        store.setId(storeDto.getId());
        store.setName(storeDto.getName());
        store.setAddress(storeDto.getAddress());
        store.setPhoneNumber(storeDto.getPhoneNumber());
        return store;
    }

    private StoreDto convertToDTO(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setName(store.getName());
        storeDto.setAddress(store.getAddress());
        storeDto.setPhoneNumber(store.getPhoneNumber());
        return storeDto;
    }
}
