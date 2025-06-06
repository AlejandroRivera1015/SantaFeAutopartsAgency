package com.autoparts.SantaFeCarsAgency.Controller;


import com.autoparts.SantaFeCarsAgency.DTO.Order.OrderDTO;
import com.autoparts.SantaFeCarsAgency.Entity.Order;
import com.autoparts.SantaFeCarsAgency.Service.Order.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO order) {
        Order responseOrder = new Order();
        try{
            System.out.println("processing order");
            responseOrder = orderService.createOrderService(order);
            if(responseOrder.getOrderStatus().equals("created")){
                return  new ResponseEntity<>(responseOrder, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(responseOrder, HttpStatus.CONFLICT);
            }

        }catch (Exception e){
            System.out.println("Error getting order : " + e.getMessage());
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
}
