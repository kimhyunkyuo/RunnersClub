package com.example.runnersclub.repository;

import com.example.runnersclub.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, Long> {


}
