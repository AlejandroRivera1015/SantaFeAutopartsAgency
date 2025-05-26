package com.autoparts.SantaFeCarsAgency.Repository.Order;

import com.autoparts.SantaFeCarsAgency.Entity.Order;
import com.autoparts.SantaFeCarsAgency.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUser(User user);
    List<Order> findByOrderDate(LocalDateTime orderDate);
}
