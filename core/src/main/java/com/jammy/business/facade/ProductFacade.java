package com.jammy.business.facade;

import com.jammy.business.adapter.ProductRecordRepositoryAdapter;
import com.jammy.domain.models.ProductRecord;
import com.jammy.domain.models.ProductRequest;

import java.util.List;

public class ProductFacade {
    private final ProductRecordRepositoryAdapter productRecordRepositoryAdapter;

    public ProductFacade(ProductRecordRepositoryAdapter productRecordRepositoryAdapter) {
        this.productRecordRepositoryAdapter = productRecordRepositoryAdapter;
    }

    public List<ProductRecord> recordProductsToDB(ProductRequest productRequest) {
        return productRecordRepositoryAdapter.recordProductsToDB(productRequest);
    }

    public List<ProductRecord> fetchAll() {
        return productRecordRepositoryAdapter.fetchAll();
    }

    public ProductRequest recordProducts(ProductRequest productRequest) {
        return productRecordRepositoryAdapter.record(productRequest);
    }
}
