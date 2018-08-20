package com.byhiras.test.core.usercase.store;

import com.byhiras.test.core.entity.Bid;

import java.util.List;

public interface BidStore {

    List<Bid> getAllBids(String itemCode);

    Bid registerBid(Bid bid);
}
