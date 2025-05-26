package com.autoparts.SantaFeCarsAgency.Service.Order;

import com.autoparts.SantaFeCarsAgency.DTO.Item.Item;
import com.autoparts.SantaFeCarsAgency.DTO.Order.OrderDTO;
import com.autoparts.SantaFeCarsAgency.Entity.Cart;
import com.autoparts.SantaFeCarsAgency.Entity.Order;
import com.autoparts.SantaFeCarsAgency.Entity.User;
import com.autoparts.SantaFeCarsAgency.Exceptions.Order.DeleteOrderException;
import com.autoparts.SantaFeCarsAgency.Exceptions.Product.AvailableProductException;
import com.autoparts.SantaFeCarsAgency.Exceptions.Product.OutOfStockException;
import com.autoparts.SantaFeCarsAgency.Repository.Cart.CartRepository;
import com.autoparts.SantaFeCarsAgency.Repository.Order.OrderRepository;
import com.autoparts.SantaFeCarsAgency.Repository.User.UserRepository;
import com.autoparts.SantaFeCarsAgency.Service.Product.ProductServiceImpl;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductServiceImpl productService;

    @Override
    @Transactional
    public Order createOrderService(OrderDTO orderRequest) {
        Order order = new Order();
        Optional<User> isUser = userRepository.findById(orderRequest.getUser().getId());

        try {
            if (isUser.isPresent()) {
                List<Item> orderRequestCart = orderRequest.getCart();
                for (Item item : orderRequestCart) {
                    if (!productService.isProductAvailable(item.getProductId())) {
                        throw new OutOfStockException("out of stock", item.getProductId());
                    }
                    if (!productService.stockAvailable(item.getProductId(), item.getQuantity())) {
                        throw new AvailableProductException("no available item", item.getProductId());
                    }
                }
                Cart cart = new Cart();
                cart.setItems(orderRequestCart);
                order.setUser(isUser.get());
                order.setIsReady(false);
                order.setOrderDate(LocalDateTime.now());
                order.setOrderStatus("created");
                order.setCart(cart);
                cart.setOrder(order);
                return orderRepository.save(order);
            }
        } catch (AvailableProductException | OutOfStockException e) {
            order.setUser(isUser.get());
            order.setOrderDate(LocalDateTime.now());
            order.setIsReady(false);


            if (e instanceof AvailableProductException) {
                order.setOrderStatus("outStockItems");
            }
            if (e instanceof OutOfStockException) {
                order.setOrderStatus("noAvailableItems");

            }

        }
        return order;

    }

    @Override
    public Order deleteOrderService(Long orderId) {
        Optional<Order> findOrder = orderRepository.findById(orderId);

        try {
            if (!findOrder.isPresent()) {
                throw new DeleteOrderException(orderId);
            }
            else {
                orderRepository.delete(findOrder.get());
                Order validationOrder = findOrder.get();
                validationOrder.setOrderStatus("deleted");
                return validationOrder;
            }
        }catch(DeleteOrderException e){
            Order validationOrder = findOrder.get();
            validationOrder.setOrderStatus("errorDeleting");
            return validationOrder ;
            }
        }


    @Override
    public List<Order> getAllOrders(Long userId){
        try{
            Optional<User> relatedUser =userRepository.findById(userId);
            if(relatedUser.isPresent()){
                List<Order> orders = orderRepository.findByUser(relatedUser.get());
                return  orders;
            }
            else{}
        }catch(Exception e){

        }
        return  new ArrayList<>();
    }
}