package com.ecommerce.ecommwebapi.service;

import com.ecommerce.ecommwebapi.dao.ProductDAO;
import com.ecommerce.ecommwebapi.models.ECommerceCommonResponse;
import com.ecommerce.ecommwebapi.models.ProductDTO;
import com.ecommerce.ecommwebapi.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts(String filter) {
        List<ProductDTO> productDTOList = null;
        if(filter!=null && !filter.isBlank()){
           var products = productRepository.findByNameOrDescriptionContainingIgnoreCase(filter,filter);
           if(products!=null && !products.isEmpty()){
               productDTOList = products.stream()
                       .map(product -> modelMapper.map(product, ProductDTO.class))
                       .toList();
           }
        }else{
            var products = productRepository.findAll();
            if(products!=null && !products.isEmpty()){
                productDTOList = products.stream()
                        .map(product -> modelMapper.map(product, ProductDTO.class))
                        .toList();
            }
        }

        return productDTOList;
    }

    public ProductDTO getProductById(Long id) {
        var product = productRepository.findById(id);
        return product.map(productDAO -> modelMapper.map(productDAO, ProductDTO.class)).orElse(null);
    }

    public ECommerceCommonResponse updateProduct(ProductDTO product) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        if (product == null || product.getProductId() == null) {
            response.setReturnCode(1);
            response.setErrorMessage("Invalid Product details");
            return response;
        }
        var dbProduct = productRepository.findById(product.getProductId()).orElse(null);
        if (dbProduct != null) {
            modelMapper.map(product, dbProduct);
            productRepository.save(dbProduct);
            response.setReturnCode(0);
            response.setErrorMessage("Success");
            return response;
        } else {
            response.setReturnCode(1);
            response.setErrorMessage("Product does not exist");
            return response;
        }
    }

    public ECommerceCommonResponse updateQuantity(ProductDTO product) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        if (product == null || product.getProductId() == null) {
            response.setReturnCode(1);
            response.setErrorMessage("Invalid Product details");
            return response;
        }
        var dbProduct = productRepository.findById(product.getProductId()).orElse(null);
        if (dbProduct != null) {
            dbProduct.setStock(product.getStock());
            productRepository.save(dbProduct);
            response.setReturnCode(0);
            response.setErrorMessage("Success");
            return response;
        } else {
            response.setReturnCode(1);
            response.setErrorMessage("Product does not exist");
            return response;
        }
    }

    public ECommerceCommonResponse createProduct(ProductDTO product) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        if (product == null ) {
            response.setReturnCode(1);
            response.setErrorMessage("Invalid Product details");
            return response;
        }
        try {
            var productDAO = modelMapper.map(product, ProductDAO.class);
            productRepository.save(productDAO);
            response.setReturnCode(0);
            response.setErrorMessage("Success");
            return response;
        }catch (Exception ex){
            response.setReturnCode(1);
            response.setErrorMessage("Failed to create product");
            return response;
        }
    }
}
