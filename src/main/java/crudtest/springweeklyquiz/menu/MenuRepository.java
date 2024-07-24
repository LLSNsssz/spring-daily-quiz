package crudtest.springweeklyquiz.menu;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByCategory(String category);
}
