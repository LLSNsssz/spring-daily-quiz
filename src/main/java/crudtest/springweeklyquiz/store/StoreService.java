package crudtest.springweeklyquiz.store;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDto createStore(StoreDto storeDto) {
        Store store = convertToEntity(storeDto);
        store = storeRepository.save(store);
        return convertToDto(store);
    }

    public StoreDto getStoreById(Long id) {
        Store store = storeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Store not found"));
        return convertToDto(store);
    }

    public List<StoreDto> getAllStores() {
        return storeRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public StoreDto updateStoreById(Long id, StoreDto storeDto) {
        Store store = storeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));
        store.setName(storeDto.getName());
        store.setAddress(storeDto.getAddress());
        store.setPhoneNumber(storeDto.getPhoneNumber());
        store = storeRepository.save(store);
        return convertToDto(store);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    private Store convertToEntity(StoreDto storeDto) {
        Store store = new Store();
        store.setId(storeDto.getId());
        store.setName(storeDto.getName());
        store.setAddress(storeDto.getAddress());
        store.setPhoneNumber(storeDto.getPhoneNumber());
        return store;
    }

    private StoreDto convertToDto(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setName(store.getName());
        storeDto.setAddress(store.getAddress());
        storeDto.setPhoneNumber(store.getPhoneNumber());
        return storeDto;
    }
}
