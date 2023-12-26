package com.jammy.business.adapter;

import com.jammy.domain.models.ProductRecord;
import com.jammy.domain.models.ProductRequest;

import java.util.List;

public interface ProductRecordRepositoryAdapter {
    List<ProductRecord> recordProductsToDB(ProductRequest productRequest);

    List<ProductRecord> fetchAll();

    ProductRequest record(ProductRequest productRequest);
}
