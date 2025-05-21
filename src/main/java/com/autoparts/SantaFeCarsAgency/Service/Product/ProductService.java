package com.autoparts.SantaFeCarsAgency.Service.Product;

import com.autoparts.SantaFeCarsAgency.Entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Boolean addProduct(Product product);
    Boolean updateProductQuantity(Long productId, Long quantity );
    Boolean updateProductName(Long productId, String name);
    Boolean updateProductPrice(Long productId, Float price);
    Boolean updateProductCategory(Long productId, String category);
    Boolean updateProductSeller(Long productId, String seller);
    Boolean updateProductDescription(Long productId, String description);
    Boolean updateProductImageUrl(Long productId, String imageUrl);
    Boolean updateProductDiscount(Long productId, Float discount);
    Boolean updateProductAvailability(Long productId, Boolean isAvailable);
    Boolean isProductAvailable(Long id);
    Boolean deleteProduct(Long id);

}
