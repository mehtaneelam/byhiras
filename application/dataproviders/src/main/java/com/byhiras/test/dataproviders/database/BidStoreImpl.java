package com.byhiras.test.dataproviders.database;

import com.byhiras.test.core.entity.Bid;
import com.byhiras.test.core.entity.BidAmount;
import com.byhiras.test.core.entity.Item;
import com.byhiras.test.core.entity.User;
import com.byhiras.test.core.usercase.store.BidStore;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BidStoreImpl implements BidStore {

    private JdbcTemplate jdbcTemplate;

    public BidStoreImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Bid> getAllBids(String itemCode) {
        String sql = "SELECT BID.ID as ID,ITEM.CODE as CODE, ITEM.DESCRIPTION as DESCRIPTION, U.USERNAME as USERNAME, BID.AMOUNT as AMOUNT FROM BID INNER JOIN ITEM ON BID.ITEM_ID = ITEM.ID inner join USER U on BID.USER_ID = U.ID where CODE = ? ";

        List<Bid> bids = new ArrayList<Bid>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,itemCode);
        for (Map row : rows) {
            User user = new User((String) row.get("USERNAME"));
            Item item = new Item((String) row.get("CODE"), (String) row.get("DESCRIPTION"));
            BidAmount bidAmount = new BidAmount((BigDecimal) row.get("AMOUNT"));
            Bid bid = new Bid(user, bidAmount, item);
            bid.setId(((BigDecimal)row.get("ID")).intValue());
            bids.add(bid);
        }

        return bids;
    }

    @Override
    public Bid registerBid(Bid bid) {
        String sql = "INSERT INTO AUCTION_TRACKER.BID (ID, ITEM_ID, USER_ID,AMOUNT)" +
                "    VALUES (AUCTION_TRACKER.BID_ID_SEQ.nextval, (SELECT ID FROM AUCTION_TRACKER.ITEM WHERE CODE = ?),  (SELECT ID FROM AUCTION_TRACKER.USER WHERE USERNAME = ?), ?)";
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update((connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bid.getBidItem().getItemCode());
            ps.setString(2, bid.getBidder().getUsername());
            ps.setBigDecimal(3, bid.getBidValue().getAmount());
            return ps;
        }, key);
        bid.setId(key.getKey().intValue());

        return bid;
    }
}
