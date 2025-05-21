package com.autoparts.SantaFeCarsAgency.Service.Product;

import com.autoparts.SantaFeCarsAgency.Entity.Product;
import com.autoparts.SantaFeCarsAgency.Exceptions.Product.OutOfStockException;
import com.autoparts.SantaFeCarsAgency.Repository.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Boolean addProduct(Product product) {
        try {
            productRepository.save(product);
            return true;

        } catch (Exception e) {
            System.out.println("Error addingProduct on ProductController: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateProductQuantity(Long productId, Long quantity) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setQuantity(quantity);
            productRepository.save(updatedProduct);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateProductName(Long productId, String name) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            Product productToUpdate = product.get();
            productToUpdate.setName(name);
            productRepository.save(productToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateProductPrice(Long productId, Float price) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setPrice(price);
            productRepository.save(updatedProduct);
            return true;
        }
        return false;

    }

    @Override
    public Boolean updateProductCategory(Long productId, String category) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setCategory(category);
            productRepository.save(updatedProduct);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateProductSeller(Long productId, String seller) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setSeller(seller);
            productRepository.save(updatedProduct);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateProductDescription(Long productId, String description) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setDescription(description);
            productRepository.save(updatedProduct);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateProductImageUrl(Long productId, String imageUrl) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setImageUrl(imageUrl);
            productRepository.save(updatedProduct);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateProductDiscount(Long productId, Float discount) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setDiscount(discount);
            productRepository.save(updatedProduct);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateProductAvailability(Long productId, Boolean isAvailable) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setIsAvailable(isAvailable);
            productRepository.save(updatedProduct);
            return true;
        }
        return false;
    }
    @Override
    public Boolean isProductAvailable(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            if (product.get().getIsAvailable()){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteProduct(Long prooductId) {
        try {
            Optional<Product> product = productRepository.findById(prooductId);
            if (product.isPresent()) {
                productRepository.delete(product.get());
            }
            return  product.isPresent();

        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean stockAvailable(Long productId, Long quantity){
        try{
            Optional<Product> stockProduct = productRepository.findById(productId);
            return stockProduct.get().getQuantity() >= quantity;
        }catch (Exception e){
          return false;
        }
    }
}
