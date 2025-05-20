package com.autoparts.SantaFeCarsAgency.Controller;


import com.autoparts.SantaFeCarsAgency.Entity.Product;
import com.autoparts.SantaFeCarsAgency.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        Boolean status = false;
        System.out.println("ProductController: "+product);
        try{
             status = productService.addProduct(product);
            return  new ResponseEntity<>(status, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update/quantity")
    public  ResponseEntity<?>  updateProduct(@RequestParam Long productId, @RequestParam Long quantity){
        try{
            productService.updateProductQuantity(productId, quantity);

            return  new ResponseEntity<>(true, HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println("Error updating product quantity: "+e.getMessage());
            return  new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/update/price")
    public ResponseEntity<?> updateProductPrice(@RequestParam Long productId, @RequestParam Float price){
        try {
            Boolean response  =  productService.updateProductPrice(productId, price);
            if( response == true)
                return new ResponseEntity<>(response, HttpStatus.OK);
            else
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            System.out.println("Error updating product price: "+e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("update/isAvailable")
    public ResponseEntity<?> updateProductStatus( @RequestParam Long productId, @RequestParam Boolean isAvailable){
        try{
            Boolean response = productService.updateProductAvailability(productId, isAvailable);
            if( response == true)
                return new ResponseEntity<>(response, HttpStatus.OK);
            else
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println("Erro updating product status on Controller: "+e.getMessage());
            return  new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestParam Long productId){
        try{
            Boolean response = productService.deleteProduct(productId);
            if(response)
                return  new ResponseEntity<>(response, HttpStatus.OK);
            else
                return  new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println("Error deleting product on Controller: "+e.getMessage());
            return  new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }



}
