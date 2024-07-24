package crudtest.springweeklyquiz.customer;

import crudtest.springweeklyquiz.menu.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
