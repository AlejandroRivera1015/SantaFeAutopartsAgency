package com.autoparts.SantaFeCarsAgency.Repository.Order;

import com.autoparts.SantaFeCarsAgency.Entity.Order;
import com.autoparts.SantaFeCarsAgency.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByUser(User user);
}
