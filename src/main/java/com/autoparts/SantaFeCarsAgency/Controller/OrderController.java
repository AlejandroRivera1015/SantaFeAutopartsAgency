package com.autoparts.SantaFeCarsAgency.Controller;


import com.autoparts.SantaFeCarsAgency.DTO.Order.OrderDTO;
import com.autoparts.SantaFeCarsAgency.Entity.Order;
import com.autoparts.SantaFeCarsAgency.Service.Order.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO order) {
        Order responseOrder = new Order();
        try{
            responseOrder = orderService.createOrderService(order);
            if(responseOrder.getOrderStatus().equals("created")){
                return  new ResponseEntity<>(responseOrder, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(responseOrder, HttpStatus.CONFLICT);
            }

        }catch (Exception e){
            responseOrder.setOrderStatus("serverError");
            return  new ResponseEntity<>(responseOrder, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public  ResponseEntity<?> deleteOrder(@RequestParam Long orderId){
        try{
            Order deletedOrderService = orderService.deleteOrderService(orderId);
            if(deletedOrderService.getOrderStatus().equals("deleted")){
                return  new ResponseEntity<>(deletedOrderService, HttpStatus.OK);
            }
            else{
                return  new ResponseEntity<>(deletedOrderService, HttpStatus.CONFLICT);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(new Order(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @GetMapping("/getAll")
    public  ResponseEntity<?> getAllOrders(@RequestParam Long userId){
        try{
            List<Order>  orders = orderService.getAllOrders(userId);
            if (!orders.isEmpty()){
                return  new ResponseEntity<>(orders, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(List.of(),HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return  new ResponseEntity<>(List.of(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
