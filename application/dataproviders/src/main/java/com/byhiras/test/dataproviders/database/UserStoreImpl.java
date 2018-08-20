package com.byhiras.test.dataproviders.database;

import com.byhiras.test.core.entity.Item;
import com.byhiras.test.core.entity.User;
import com.byhiras.test.core.usercase.store.UserStore;
import com.byhiras.test.dataproviders.mappers.ItemRowMapper;
import com.byhiras.test.dataproviders.mappers.UserRowMapper;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserStoreImpl implements UserStore {

    private JdbcTemplate jdbcTemplate;

    public UserStoreImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUser(String username) {
        // as user domain has not been enriched

        String sql = "SELECT * FROM USER WHERE USERNAME = ?";
        try{
            User user = (User) jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Item> getItemsBidByUser(String username) {
        String sql = "SELECT ITEM.CODE as CODE, ITEM.DESCRIPTION as DESCRIPTION FROM BID INNER JOIN ITEM ON BID.ITEM_ID = ITEM.ID inner join USER U on BID.USER_ID = U.ID where u.USERNAME = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,username);
        List<Item> items = new ArrayList<>();
        for (Map row : rows) {
            Item item = new Item((String) row.get("CODE"), (String) row.get("DESCRIPTION"));
            items.add(item);
        }

        return items;
    }


}
