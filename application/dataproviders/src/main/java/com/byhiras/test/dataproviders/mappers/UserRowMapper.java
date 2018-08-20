package com.byhiras.test.dataproviders.mappers;

import com.byhiras.test.core.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString("USERNAME"));
        return user;
    }
}
