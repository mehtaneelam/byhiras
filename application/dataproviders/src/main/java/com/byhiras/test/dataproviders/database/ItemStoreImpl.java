package com.byhiras.test.dataproviders.database;

import com.byhiras.test.core.entity.Item;
import com.byhiras.test.core.usercase.store.ItemStore;
import com.byhiras.test.dataproviders.mappers.ItemRowMapper;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ItemStoreImpl implements ItemStore {
    private JdbcTemplate jdbcTemplate;

    public ItemStoreImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item getItem(String itemCode) {
        String sql = "SELECT * FROM ITEM WHERE CODE = ?";
        try {
            Item item = (Item) jdbcTemplate.queryForObject(sql, new ItemRowMapper(), itemCode);
            return item;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
