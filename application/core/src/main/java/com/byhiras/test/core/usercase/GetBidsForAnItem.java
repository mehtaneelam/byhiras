package com.byhiras.test.core.usercase;

import com.byhiras.test.core.entity.Bid;
import com.byhiras.test.core.usercase.exceptions.ItemNotFoundException;
import com.byhiras.test.core.usercase.store.BidStore;
import com.byhiras.test.core.usercase.store.ItemStore;

import java.util.List;
import java.util.Optional;

public class GetBidsForAnItem {

    private ItemStore itemStore;
    private BidStore bidStore;

    public GetBidsForAnItem(ItemStore itemStore, BidStore bidStore) {
        this.itemStore = itemStore;
        this.bidStore = bidStore;
    }

    public List<Bid> getAllBidsForAnItem(String itemCode){
        Optional.ofNullable(itemStore.getItem(itemCode))
                .orElseThrow(ItemNotFoundException::new);
        return bidStore.getAllBids(itemCode);
    }




}
