package com.ecommerce.ecommwebapi.controller;

import com.ecommerce.ecommwebapi.models.ECommerceCommonResponse;
import com.ecommerce.ecommwebapi.models.ProductDTO;
import com.ecommerce.ecommwebapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("products")
    public List<ProductDTO> getAllProducts(@RequestParam(value = "filter", required = false) String filter){
       return productService.getAllProducts(filter);
    }
    @GetMapping("product/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PutMapping("product" )
    public ECommerceCommonResponse updateProduct(@RequestBody ProductDTO product){
        return productService.updateProduct(product);
    }
    @PostMapping("product" )
    public ECommerceCommonResponse createProduct(@RequestBody ProductDTO product){
        return productService.createProduct(product);
    }
//    @PutMapping("restockProduct" )
//    public ECommerceCommonResponse restockProduct(@RequestBody ProductDTO product) {
//        return productService.updateQuantity(product);
//    }

}
