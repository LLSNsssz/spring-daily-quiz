package crudtest.springweeklyquiz.order;

import crudtest.springweeklyquiz.store.Store;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStoreAndCreatedAtBetween(Store store, LocalDateTime start, LocalDateTime end);
}
