package com.mb.DineEase.respository.order;

import com.mb.DineEase.model.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

}
