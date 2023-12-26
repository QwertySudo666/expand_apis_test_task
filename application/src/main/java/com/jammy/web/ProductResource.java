package com.jammy.web;

import com.jammy.business.facade.ProductFacade;
import com.jammy.domain.models.ProductRecord;
import com.jammy.domain.models.ProductRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductResource {
    private final ProductFacade productFacade;

    public ProductResource(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping("add")
    List<ProductRecord> add(@RequestBody ProductRequest productRequest) {
        return productFacade.recordProductsToDB(productRequest);
//        return productFacade.recordProducts(productRequest);
    }

    @GetMapping("all")
    List<ProductRecord> fetchAll() {
        return productFacade.fetchAll();
    }
}
