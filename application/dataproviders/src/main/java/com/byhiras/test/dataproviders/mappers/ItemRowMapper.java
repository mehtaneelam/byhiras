package com.byhiras.test.dataproviders.mappers;

import com.byhiras.test.core.entity.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item(rs.getString("CODE"), rs.getString("DESCRIPTION"));
        return item;
    }
}
