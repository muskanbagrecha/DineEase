package com.mb.DineEase.controller.restaurant.orders;

import com.mb.DineEase.model.order.Order;
import com.mb.DineEase.request.order.OrderRequest;
import com.mb.DineEase.securityconfig.JwtUtil;
import com.mb.DineEase.service.CustomerUserDetails;
import com.mb.DineEase.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping()
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String user = userDetails.getUsername();
            orderService.createOrder(user, request.getRestaurantId(), request.getItems());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Order placed successfully");
    }

}
