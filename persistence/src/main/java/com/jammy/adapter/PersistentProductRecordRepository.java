package com.jammy.adapter;

import com.jammy.business.adapter.ProductRecordRepositoryAdapter;
import com.jammy.domain.models.ProductRecord;
import com.jammy.domain.models.ProductRequest;
import com.jammy.entities.ProductRecordEntity;
import com.jammy.repository.ProductRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersistentProductRecordRepository implements ProductRecordRepositoryAdapter {
    private final JdbcTemplate jdbcTemplate;

    private final ProductRepository productRepository;

    public PersistentProductRecordRepository(JdbcTemplate jdbcTemplate, ProductRepository productRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductRecord> recordProductsToDB(ProductRequest productRequest) {
        jdbcTemplate.execute(prepareCreateTableQuery(productRequest.getTable()));
        jdbcTemplate.execute(prepareInsertQuery(productRequest));
        return fetchAll();
    }

    @Override
    public List<ProductRecord> fetchAll() {
        return jdbcTemplate.query(prepareSelectAllQuery(), new ProductRecordRowMapper());
    }

    @Override
    public ProductRequest record(ProductRequest productRequest) {
        var productEntities = productRequest.getRecords().stream().map(it -> new ProductRecordEntity(
                it.getId(),
                it.getEntryDate(),
                it.getItemCode(),
                it.getItemName(),
                it.getItemQuantity(),
                it.getStatus()
        )).collect(Collectors.toList());
        productRepository.saveAll(productEntities);
        return productRequest;
    }

    private String prepareCreateTableQuery(String tableName) {
        return "CREATE TABLE IF NOT EXISTS " + tableName + "(\n" + "id VARCHAR(36) PRIMARY KEY,\n" + "entryDate DATE,\n" + "itemCode VARCHAR(20),\n" + "itemName VARCHAR(255),\n" + "itemQuantity INT,\n" + "status VARCHAR(50)\n" + ");";
    }

    private String prepareInsertQuery(ProductRequest productRequest) {
        var query = "INSERT INTO " + productRequest.getTable() + " (id, entryDate, itemCode, itemName, itemQuantity, status) VALUES";
        List<String> values = productRequest.getRecords().stream().map(record -> String.format("('%s', '%s', '%s', '%s', %s, '%s')",
                UUID.randomUUID(),
                Timestamp.from(record.getEntryDate().toInstant()),
                record.getItemCode(),
                record.getItemName(),
                record.getItemQuantity(),
                record.getStatus())).toList();
        query = query + String.join(",\n", values) + ";";
        return query;
    }

    private String prepareSelectAllQuery() {
        return "SELECT * FROM products";
    }
}

class ProductRecordRowMapper implements RowMapper<ProductRecord> {
    @Override
    public ProductRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductRecord productRecord = new ProductRecord();
        productRecord.setId(UUID.fromString(rs.getString("id")));
        productRecord.setEntryDate(rs.getDate("entryDate"));
        productRecord.setItemCode(rs.getString("itemCode"));
        productRecord.setItemName(rs.getString("itemName"));
        productRecord.setItemQuantity(rs.getString("itemQuantity"));
        productRecord.setStatus(rs.getString("status"));
        return productRecord;
    }
}