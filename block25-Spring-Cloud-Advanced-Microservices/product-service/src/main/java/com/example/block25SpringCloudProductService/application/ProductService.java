package com.example.block25SpringCloudProductService.application;

import com.example.block25SpringCloudProductService.infrastructure.models.response.ProductResponse;
import com.example.block25SpringCloudProductService.infrastructure.repository.ProductRepository;
import com.example.block25SpringCloudProductService.infrastructure.models.request.ProductRequest;
import com.example.block25SpringCloudProductService.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} has been saved", product.getId());
        return entityToResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : productList) {
            ProductResponse productResponse = entityToResponse(product);
            productResponses.add(productResponse);
        }

        return productResponses;
    }

    public ProductResponse getProductById(String id) {
        var product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return entityToResponse(product);
    }

    public ProductResponse updateProduct(ProductRequest request, String id) {
        var productToUpdate = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        productToUpdate.setName(request.getName());
        productToUpdate.setDescription(request.getDescription());
        productToUpdate.setPrice(request.getPrice());

        var productUpdated = productRepository.save(productToUpdate);
        log.info("Product {} has been updated successfully", productUpdated.getId());
        return entityToResponse(productUpdated);
    }

    public void deleteProduct(String id) {
        var productToDelete = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        productRepository.delete(productToDelete);
    }

    private ProductResponse entityToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
