package com.jammy.domain.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProductRequest {
    private String table;
    private List<ProductRecord> records;

    public ProductRequest() {
    }

    public ProductRequest(String table, List<ProductRecord> records) {
        this.table = table;
        this.records = records;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<ProductRecord> getRecords() {
        return records;
    }

    public void setRecords(List<ProductRecord> records) {
        this.records = records;
    }
}
