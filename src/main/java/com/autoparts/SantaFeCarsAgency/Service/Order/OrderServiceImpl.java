package com.autoparts.SantaFeCarsAgency.Service.Order;

import com.autoparts.SantaFeCarsAgency.DTO.Item.Item;
import com.autoparts.SantaFeCarsAgency.DTO.Order.OrderDTO;
import com.autoparts.SantaFeCarsAgency.Entity.Cart;
import com.autoparts.SantaFeCarsAgency.Entity.Order;
import com.autoparts.SantaFeCarsAgency.Entity.User;
import com.autoparts.SantaFeCarsAgency.Exceptions.Product.AvailableProductException;
import com.autoparts.SantaFeCarsAgency.Exceptions.Product.OutOfStockException;
import com.autoparts.SantaFeCarsAgency.Repository.Cart.CartRepository;
import com.autoparts.SantaFeCarsAgency.Repository.Order.OrderRepository;
import com.autoparts.SantaFeCarsAgency.Repository.User.UserRepository;
import com.autoparts.SantaFeCarsAgency.Service.Product.ProductServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public Order createOrderService(OrderDTO orderResquest){
        Order order = new Order();
        Optional<User> isUser = userRepository.findById(orderResquest.getUser().getId());

        try {
            if(isUser.isPresent()) {
                List<Item> orderRequestCart = orderResquest.getCart();
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
        }catch (AvailableProductException | OutOfStockException e){
            order.setUser(isUser.get());
            order.setOrderDate(LocalDateTime.now());
            order.setIsReady(false);


            if(e instanceof AvailableProductException){
                order.setOrderStatus("outStockItems");
           }
           if(e instanceof  OutOfStockException){
                order.setOrderStatus("noAvailableItems");
           }

        }
        finally {
            return  order;
        }

    }

}
